package com.setxpro.setxpro.services;

import com.setxpro.setxpro.domain.product.Product;
import com.setxpro.setxpro.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> findAllProducts() {
       return this.productRepository.findAll();
    }

    public Optional<Product> findOneProduct(String id) {
       return this.productRepository.findById(id);
    }

    public Optional<Product> updateProduct(String id, Product newProduct) {
        Product productExists = productRepository.findById(id).orElseThrow(() -> new ResolutionException("Produto não encontrado com o ID: " + id));
        productExists.setName(newProduct.getName());
        productExists.setPrice_in_cents(newProduct.getPrice_in_cents());
        return Optional.of(productRepository.save(productExists));
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

}
