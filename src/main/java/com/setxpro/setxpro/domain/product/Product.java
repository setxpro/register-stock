package com.setxpro.setxpro.domain.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

// Representação da tabela no banco de dados....
// Usar repositories para manipular os dados..
@Table(name="product")
@Entity(name="product")
@Getter // Lombok gera automaticamente os getters e setters
@Setter
@AllArgsConstructor // Cria automaticamente o constructor e seta as variáveis
@NoArgsConstructor // Construtor sem argumentos ...
@EqualsAndHashCode(of="id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer price_in_cents;

    public Product(RequestProduct requestProduct) {
        this.name = requestProduct.name();
        this.price_in_cents = requestProduct.price_in_cents();
    }

}
