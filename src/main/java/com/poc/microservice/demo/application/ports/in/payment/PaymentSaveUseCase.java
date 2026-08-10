package com.poc.microservice.demo.application.ports.in.payment;

import com.poc.microservice.demo.application.exceptions.ApplicationException;
import com.poc.microservice.demo.domain.payment.Payment;

@FunctionalInterface
public interface PaymentSaveUseCase {
    Payment save(Payment payment) throws ApplicationException;
}
