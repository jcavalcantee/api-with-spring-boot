package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.models.Produto;
import com.example.springboot.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	public ResponseEntity<Produto> saveProduct(@RequestBody Produto produto ) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> listProducts() {
		
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> productByID(@PathVariable (value="id") Long id) {
		
		Optional<Produto> product0 = produtoRepository.findById(id);
		
		if (product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(product0.get());
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> updateProduto(@PathVariable (value="id") Long id, @RequestBody Produto produto) {
		
		Optional<Produto> product0 = produtoRepository.findById(id);
		
		if (product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
		}
		
		Produto exProd = product0.get();

		exProd.setNome(produto.getNome());
	    exProd.setValor(produto.getValor());
	    
	    Produto updatedProduct = produtoRepository.save(exProd);
		
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(updatedProduct));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable (value="id") Long id) {
		
		Optional<Produto> product0 = produtoRepository.findById(id);
		
		if (product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
		}
		
		produtoRepository.delete(product0.get());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successgully!");
	}
}
