package com.poc.microservice.demo.adapters.in.api.rest.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        @NotBlank @Size(max = 100) String idTransaction,
        @NotBlank @Size(min = 2, max = 50) String nombre,
        @NotNull @Positive @Digits(integer = 17, fraction = 2) BigDecimal monto
) {
}
