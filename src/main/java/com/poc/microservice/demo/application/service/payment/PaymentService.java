package com.poc.microservice.demo.application.service.payment;

import com.poc.microservice.demo.application.exceptions.ApplicationException;
import com.poc.microservice.demo.application.ports.in.payment.PaymentGetAllUseCase;
import com.poc.microservice.demo.application.ports.in.payment.PaymentSaveUseCase;
import com.poc.microservice.demo.application.ports.out.payment.PaymentGetAllPort;
import com.poc.microservice.demo.application.ports.out.payment.PaymentSavePort;
import com.poc.microservice.demo.domain.payment.Payment;

import java.util.List;

public class PaymentService implements PaymentSaveUseCase, PaymentGetAllUseCase {

    private final PaymentGetAllPort paymentGetAllPort;
    private final PaymentSavePort paymentSavePort;

    public PaymentService(PaymentGetAllPort paymentGetAllPort, PaymentSavePort paymentSavePort) {
        this.paymentGetAllPort = paymentGetAllPort;
        this.paymentSavePort = paymentSavePort;
    }

    @Override
    public List<Payment> getAll() throws ApplicationException {
        return paymentGetAllPort.getAll();
    }

    @Override
    public Payment save(Payment payment) throws ApplicationException {
        return paymentSavePort.save(payment);
    }
}
