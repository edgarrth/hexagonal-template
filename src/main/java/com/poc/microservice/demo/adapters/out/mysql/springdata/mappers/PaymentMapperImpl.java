package com.poc.microservice.demo.adapters.out.mysql.springdata.mappers;

import com.poc.microservice.demo.adapters.out.mysql.springdata.entity.PaymentMySQLEntity;
import com.poc.microservice.demo.domain.payment.Payment;

import java.util.List;
import java.util.Objects;

public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment toDomain(PaymentMySQLEntity entity) {
        Objects.requireNonNull(entity, "Payment entity must not be null");

        Payment payment = new Payment(entity.getIdTransaction(), entity.getNombre(), entity.getMonto());
        payment.setId(entity.getId());
        return payment;
    }

    @Override
    public PaymentMySQLEntity toEntity(Payment payment) {
        Objects.requireNonNull(payment, "Payment must not be null");
        return new PaymentMySQLEntity(
                payment.getId(),
                payment.getIdTransaction(),
                payment.getNombre(),
                payment.getMonto()
        );
    }

    @Override
    public List<Payment> toDomainList(List<PaymentMySQLEntity> entities) {
        return entities.stream().map(this::toDomain).toList();
    }
}
