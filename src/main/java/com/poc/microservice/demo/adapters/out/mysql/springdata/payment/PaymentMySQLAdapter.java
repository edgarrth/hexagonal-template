package com.poc.microservice.demo.adapters.out.mysql.springdata.payment;

import com.poc.microservice.demo.adapters.out.mysql.springdata.entity.PaymentMySQLEntity;
import com.poc.microservice.demo.adapters.out.mysql.springdata.mappers.PaymentMapper;
import com.poc.microservice.demo.adapters.out.mysql.springdata.repository.PaymentMySQLRepository;
import com.poc.microservice.demo.application.exceptions.ApplicationException;
import com.poc.microservice.demo.application.ports.out.payment.PaymentGetAllPort;
import com.poc.microservice.demo.application.ports.out.payment.PaymentSavePort;
import com.poc.microservice.demo.domain.payment.Payment;

import java.util.List;

public class PaymentMySQLAdapter implements PaymentGetAllPort, PaymentSavePort {

    private final PaymentMySQLRepository paymentMySQLRepository;
    private final PaymentMapper paymentMapper;

    public PaymentMySQLAdapter(PaymentMySQLRepository paymentMySQLRepository, PaymentMapper paymentMapper) {
        this.paymentMySQLRepository = paymentMySQLRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public List<Payment> getAll() throws ApplicationException {
        try {
            return paymentMapper.toDomainList(paymentMySQLRepository.findAll());
        } catch (RuntimeException exception) {
            throw new ApplicationException("Unable to retrieve payments", exception);
        }
    }

    @Override
    public Payment save(Payment payment) throws ApplicationException {
        try {
            PaymentMySQLEntity savedEntity = paymentMySQLRepository.save(paymentMapper.toEntity(payment));
            return paymentMapper.toDomain(savedEntity);
        } catch (RuntimeException exception) {
            throw new ApplicationException("Unable to save payment", exception);
        }
    }
}
