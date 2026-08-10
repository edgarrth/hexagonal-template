package com.poc.microservice.demo.domain.payment;

import com.poc.microservice.demo.domain.base.Domain;

import java.math.BigDecimal;

public class Payment extends Domain {

    private String idTransaction;
    private String nombre;
    private BigDecimal monto;

    public Payment() {
        super();
    }

    public Payment(String idTransaction, String nombre, BigDecimal monto) {
        this.idTransaction = idTransaction;
        this.nombre = nombre;
        this.monto = monto;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
