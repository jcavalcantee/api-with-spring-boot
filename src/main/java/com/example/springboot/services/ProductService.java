package com.example.springboot.services;

import com.example.springboot.dtos.ProductDto;
import com.example.springboot.models.Product;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    public Product createProduct(ProductDto productDto) {
        Product newProduct = new Product(productDto);
        this.saveProduct(newProduct);
        return newProduct;
    }

    public ResponseEntity<?> updateProduct(Long id, Product product) {
        Optional<Product> product0 = this.productRepository.findById(id);

        if(product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product ID provided does not exist");
        }
        productRepository.delete(product0.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully!");
    }

    public Product getProductById(Long id) throws Exception{
        return this.productRepository.findById(id).orElseThrow(() -> new Exception("Product not found!"));
    }

    public List<Product> listAllProducts() {
        return this.productRepository.findAll();
    }

    public void deleteProductById(Long id) {
        try {
            this.productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Product ID provided does not exists");
        }
    }
}
