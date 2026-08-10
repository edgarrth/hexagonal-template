package com.poc.microservice.demo.adapters.in.api.rest.controllers.payment;

import com.poc.microservice.demo.adapters.in.api.rest.commons.CustomResponse;
import com.poc.microservice.demo.adapters.in.api.rest.dtos.PaymentRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentController {

    @GetMapping
    ResponseEntity<CustomResponse> findAll();

    @PostMapping
    ResponseEntity<CustomResponse> save(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO);
}
