package com.poc.microservice.demo.adapters.in.api.rest.mappers;

import com.poc.microservice.demo.adapters.in.api.rest.dtos.PaymentRequestDTO;
import com.poc.microservice.demo.adapters.in.api.rest.dtos.PaymentResponseDTO;
import com.poc.microservice.demo.domain.payment.Payment;

import java.util.List;

public interface PaymentMapper {

    Payment toDomain(PaymentRequestDTO paymentRequestDTO);

    PaymentResponseDTO toResponse(Payment payment);

    List<PaymentResponseDTO> toResponseList(List<Payment> payments);
}
