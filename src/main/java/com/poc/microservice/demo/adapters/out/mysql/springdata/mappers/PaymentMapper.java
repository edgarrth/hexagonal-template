package com.poc.microservice.demo.adapters.out.mysql.springdata.mappers;

import com.poc.microservice.demo.adapters.out.mysql.springdata.entity.PaymentMySQLEntity;
import com.poc.microservice.demo.domain.payment.Payment;

import java.util.List;

public interface PaymentMapper {
    Payment toDomain(PaymentMySQLEntity entity);

    PaymentMySQLEntity toEntity(Payment payment);

    List<Payment> toDomainList(List<PaymentMySQLEntity> entities);
}
