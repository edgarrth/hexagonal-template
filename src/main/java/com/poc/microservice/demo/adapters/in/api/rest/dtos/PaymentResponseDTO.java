package com.poc.microservice.demo.adapters.in.api.rest.dtos;

import java.math.BigDecimal;

public record PaymentResponseDTO(
        Long id,
        String idTransaction,
        String nombre,
        BigDecimal monto
) {
}
