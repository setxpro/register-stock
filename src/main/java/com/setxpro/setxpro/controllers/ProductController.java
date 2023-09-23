package com.setxpro.setxpro.controllers;

import com.setxpro.setxpro.domain.product.Product;
import com.setxpro.setxpro.domain.product.ProductRepository;
import com.setxpro.setxpro.domain.product.RequestProduct;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // ResponseEntity -> res. como no nodejs
    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = productRepository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneProduct(@PathVariable String id) {
        var oneProduct = productRepository.findById(id);
        return ResponseEntity.ok(oneProduct);
    }

    // @Valid vai fazer a validação do bory atraves dp RequestProduct
    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = new Product(data);
        productRepository.save(newProduct);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody RequestProduct data) {
        Optional<Product> optionalProduct = productRepository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteOne(@PathVariable String id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Produto id: " + id + " foi deletado com sucesso.");
    }
}
