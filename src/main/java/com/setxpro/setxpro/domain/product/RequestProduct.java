package com.setxpro.setxpro.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

// DTO -> apenas para transferir os dados
// Validação dos dados.
// NotBlank O bin faz a validação
public record RequestProduct(
        String id,
       @NotBlank
       String name,
       @NotNull
        Integer price_in_cents) {
}
