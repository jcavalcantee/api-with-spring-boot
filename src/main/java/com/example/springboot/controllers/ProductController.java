package com.example.springboot.controllers;

import java.util.List;

import com.example.springboot.dtos.ProductDto;
import com.example.springboot.models.Product;
import com.example.springboot.services.ProductService;
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

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody ProductDto productDto) {
		Product newProduct = productService.createProduct(productDto);
		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> listProducts() {
		var productList = this.productService.listAllProducts();
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product> productByID(@PathVariable (value="id") Long id) throws Exception {
		var productForId = this.productService.getProductById(id);
		return new ResponseEntity<>(productForId, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProduto(@PathVariable (value="id") Long id, @RequestBody Product product) throws Exception{
		var productUpdate = this.productService.updateProduct(id, product);
		return new ResponseEntity<>(productUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable (value="id") Long id) {
		this.productService.deleteProductById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
