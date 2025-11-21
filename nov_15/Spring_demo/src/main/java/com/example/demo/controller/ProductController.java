package com.example.demo.controller;

import com.example.demo.request.Product;
import com.example.demo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null); 
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setCost(updatedProduct.getCost());
            return productRepository.save(product);
        }).orElse(null); 
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product deleted successfully";
        } else {
            return "Product not found";
        }
    }
}
