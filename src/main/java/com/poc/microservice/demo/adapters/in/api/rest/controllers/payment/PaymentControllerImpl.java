package com.poc.microservice.demo.adapters.in.api.rest.controllers.payment;

import com.poc.microservice.demo.adapters.in.api.rest.commons.CustomResponse;
import com.poc.microservice.demo.adapters.in.api.rest.dtos.PaymentRequestDTO;
import com.poc.microservice.demo.adapters.in.api.rest.mappers.PaymentMapper;
import com.poc.microservice.demo.application.ports.in.payment.PaymentGetAllUseCase;
import com.poc.microservice.demo.application.ports.in.payment.PaymentSaveUseCase;
import com.poc.microservice.demo.domain.payment.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment-management/v1/payments")
public class PaymentControllerImpl extends BaseController implements PaymentController {

    private final PaymentGetAllUseCase paymentGetAllUseCase;
    private final PaymentSaveUseCase paymentSaveUseCase;
    private final PaymentMapper paymentMapper;

    public PaymentControllerImpl(
            PaymentGetAllUseCase paymentGetAllUseCase,
            PaymentSaveUseCase paymentSaveUseCase,
            PaymentMapper paymentMapper
    ) {
        this.paymentGetAllUseCase = paymentGetAllUseCase;
        this.paymentSaveUseCase = paymentSaveUseCase;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public ResponseEntity<CustomResponse> findAll() {
        try {
            List<Payment> payments = paymentGetAllUseCase.getAll();
            return payments.isEmpty() ? noContent() : ok("Payment", "FIND_ALL", paymentMapper.toResponseList(payments));
        } catch (Exception exception) {
            return internalError("Payment", "FIND_ALL");
        }
    }

    @Override
    public ResponseEntity<CustomResponse> save(PaymentRequestDTO paymentRequestDTO) {
        try {
            Payment payment = paymentMapper.toDomain(paymentRequestDTO);
            Payment savedPayment = paymentSaveUseCase.save(payment);
            return created("Payment", "SAVE", paymentMapper.toResponse(savedPayment));
        } catch (Exception exception) {
            return internalError("Payment", "SAVE");
        }
    }
}
