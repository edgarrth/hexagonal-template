package com.poc.microservice.demo.adapters.in.api.rest.mappers;

import com.poc.microservice.demo.adapters.in.api.rest.dtos.PaymentRequestDTO;
import com.poc.microservice.demo.adapters.in.api.rest.dtos.PaymentResponseDTO;
import com.poc.microservice.demo.domain.payment.Payment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment toDomain(PaymentRequestDTO paymentRequestDTO) {
        return new Payment(
                paymentRequestDTO.idTransaction(),
                paymentRequestDTO.nombre(),
                paymentRequestDTO.monto()
        );
    }

    @Override
    public PaymentResponseDTO toResponse(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getIdTransaction(),
                payment.getNombre(),
                payment.getMonto()
        );
    }

    @Override
    public List<PaymentResponseDTO> toResponseList(List<Payment> payments) {
        return payments.stream().map(this::toResponse).toList();
    }
}
