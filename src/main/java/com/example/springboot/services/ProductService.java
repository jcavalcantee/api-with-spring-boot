package com.example.springboot.services;

import com.example.springboot.controllers.ProductController;
import com.example.springboot.dtos.ProductDto;
import com.example.springboot.models.Product;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    public Product createProduct(ProductDto productDto) throws Exception {
        Product newProduct = new Product(productDto);
        this.saveProduct(newProduct);
        newProduct.add(linkTo(methodOn(ProductController.class).saveProduct(productDto)).withSelfRel());
        newProduct.add(linkTo(methodOn(ProductController.class).productByID(newProduct.getId())).withRel("Find By ID"));
        newProduct.add(linkTo(methodOn(ProductController.class).productByName(newProduct.getName())).withRel("Find By Name"));
        newProduct.add(linkTo(methodOn(ProductController.class).listProducts()).withRel("Find All"));
        newProduct.add(linkTo(methodOn(ProductController.class).updateProduct(newProduct.getId(), newProduct)).withRel("Update Product"));
        newProduct.add(linkTo(methodOn(ProductController.class).deleteProduct(newProduct.getId())).withRel("Delete Product"));

        return newProduct;
    }

    public Product updateProduct(Long id, Product product) throws Exception {
        Product existProduct = productRepository.findById(id).orElseThrow(
                () -> new Exception("Product not found with ID: " + id));

        existProduct.setName(product.getName());
        existProduct.setPrice(product.getPrice());

        existProduct.add(linkTo(methodOn(ProductController.class).updateProduct(existProduct.getId(), existProduct)).withSelfRel());
        existProduct.add(linkTo(methodOn(ProductController.class).productByID(existProduct.getId())).withRel("Find By ID"));
        existProduct.add(linkTo(methodOn(ProductController.class).productByName(existProduct.getName())).withRel("Find By Name"));
        existProduct.add(linkTo(methodOn(ProductController.class).listProducts()).withRel("Find All"));
        existProduct.add(linkTo(methodOn(ProductController.class).deleteProduct(existProduct.getId())).withRel("Delete Product"));

        return productRepository.save(existProduct);
    }

    public Product getProductById(Long id) throws Exception {
        var product = this.productRepository.findById(id).orElseThrow(() -> new Exception("Product not found!"));
        product.add(linkTo(methodOn(ProductController.class).productByID(product.getId())).withSelfRel());
        product.add(linkTo(methodOn(ProductController.class).productByName(product.getName())).withRel("Find By Name"));
        product.add(linkTo(methodOn(ProductController.class).listProducts()).withRel("Find All"));
        product.add(linkTo(methodOn(ProductController.class).updateProduct(product.getId(), product)).withRel("Update Product"));
        product.add(linkTo(methodOn(ProductController.class).productByName(product.getName())).withRel("Delete Product"));
        return product;
    }

    public Product getProductByName(String name) throws Exception {
        var product = productRepository.findByName(name);
        product.add(linkTo(methodOn(ProductController.class).productByName(product.getName())).withSelfRel());
        product.add(linkTo(methodOn(ProductController.class).productByID(product.getId())).withRel("Find By ID"));
        product.add(linkTo(methodOn(ProductController.class).listProducts()).withRel("Find All"));
        product.add(linkTo(methodOn(ProductController.class).updateProduct(product.getId(), product)).withRel("Update Product"));
        product.add(linkTo(methodOn(ProductController.class).productByName(product.getName())).withRel("Delete Product"));
        return product;
    }

    public List<Product> listAllProducts() {
        var product = this.productRepository.findAll();
        product
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(ProductController.class).listProducts()).withSelfRel());
                        p.add(linkTo(methodOn(ProductController.class).productByID(p.getId())).withRel("Find By ID"));
                        p.add(linkTo(methodOn(ProductController.class).productByName(p.getName())).withRel("Find By Name"));
                        p.add(linkTo(methodOn(ProductController.class).updateProduct(p.getId(), new Product())).withRel("Update Product"));
                        p.add(linkTo(methodOn(ProductController.class).deleteProduct(p.getId())).withRel("Delete Product"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return product;
    }

    public ResponseEntity<Product> deleteProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            this.productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

