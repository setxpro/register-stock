package com.setxpro.setxpro.controllers;

import com.setxpro.setxpro.domain.product.Product;
import com.setxpro.setxpro.domain.product.RequestProduct;
import com.setxpro.setxpro.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ResponseEntity -> res. como no nodejs
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = this.productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getOneProduct(@PathVariable String id) {
        var oneProduct = productService.findOneProduct(id);
        return new ResponseEntity<>(oneProduct, HttpStatus.OK);
    }

    // @Valid vai fazer a validação do bory atraves dp RequestProduct
    @PostMapping
    public ResponseEntity<Product> registerProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = new Product(data);
        productService.createProduct(newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Product>> updateProduct(@PathVariable String id, @RequestBody Product newProduct) {
        Optional<Product> productUpdated = productService.updateProduct(id, newProduct);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable String id) {
       productService.deleteProduct(id);
       return ResponseEntity.noContent().build();
    }
}
