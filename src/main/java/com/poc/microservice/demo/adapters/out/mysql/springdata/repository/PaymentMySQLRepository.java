package com.poc.microservice.demo.adapters.out.mysql.springdata.repository;

import com.poc.microservice.demo.adapters.out.mysql.springdata.entity.PaymentMySQLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMySQLRepository extends JpaRepository<PaymentMySQLEntity, Long> {
}
