package com.setxpro.setxpro.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

// Manipulação do banco de dados..
// <Product, String> = <EntityName, Type_primary_key>
public interface ProductRepository extends JpaRepository<Product, String> { }
