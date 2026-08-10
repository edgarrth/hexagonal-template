package com.poc.microservice.demo.adapters.out.mysql.springdata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity(name = "PaymentMySQLEntity")
@Table(name = "TBL_PAYMENT")
public class PaymentMySQLEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ID")
    private Long id;

    @Column(name = "ID_TRANSACTION", nullable = false, unique = true, length = 100)
    private String idTransaction;

    @Size(min = 2, max = 50)
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "MONTO", nullable = false, precision = 19, scale = 2)
    private BigDecimal monto;

    protected PaymentMySQLEntity() {
    }

    public PaymentMySQLEntity(Long id, String idTransaction, String nombre, BigDecimal monto) {
        this.id = id;
        this.idTransaction = idTransaction;
        this.nombre = nombre;
        this.monto = monto;
    }

    public Long getId() {
        return id;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getMonto() {
        return monto;
    }
}
