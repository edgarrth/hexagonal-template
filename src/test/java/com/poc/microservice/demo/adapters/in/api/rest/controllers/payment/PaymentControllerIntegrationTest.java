package com.poc.microservice.demo.adapters.in.api.rest.controllers.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class PaymentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateAndRetrievePayment() throws Exception {
        mockMvc.perform(post("/payment-management/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "idTransaction": "txn-001",
                                  "nombre": "Juan Perez",
                                  "monto": 123.45
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.response.id").isNumber())
                .andExpect(jsonPath("$.response.idTransaction").value("txn-001"))
                .andExpect(jsonPath("$.response.nombre").value("Juan Perez"))
                .andExpect(jsonPath("$.response.monto").value(123.45));

        mockMvc.perform(get("/payment-management/v1/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response[0].idTransaction").value("txn-001"));
    }

    @Test
    void shouldReturnNoContentWhenNoPaymentsExist() throws Exception {
        mockMvc.perform(get("/payment-management/v1/payments"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldRejectInvalidPayment() throws Exception {
        mockMvc.perform(post("/payment-management/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "idTransaction": "",
                                  "nombre": "A",
                                  "monto": -10
                                }
                                """))
                .andExpect(status().isBadRequest());
    }
}
