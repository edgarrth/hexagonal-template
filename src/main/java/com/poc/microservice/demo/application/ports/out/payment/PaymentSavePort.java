package com.poc.microservice.demo.application.ports.out.payment;

import com.poc.microservice.demo.application.exceptions.ApplicationException;
import com.poc.microservice.demo.domain.payment.Payment;

@FunctionalInterface
public interface PaymentSavePort {

    Payment save(Payment payment) throws ApplicationException;
}
