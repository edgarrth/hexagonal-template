package com.poc.microservice.demo.adapters.in.api.rest.controllers.payment;

import com.poc.microservice.demo.adapters.in.api.rest.commons.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected ResponseEntity<CustomResponse> noContent() {
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<CustomResponse> internalError(String api, String method) {
        return ResponseEntity.internalServerError().body(
                new CustomResponse(
                        api,
                        method,
                        HttpStatus.INTERNAL_SERVER_ERROR.name(),
                        "Internal Runtime Error",
                        null
                )
        );
    }

    protected ResponseEntity<CustomResponse> ok(String api, String method, Object response) {
        return ResponseEntity.ok(
                new CustomResponse(
                        api,
                        method,
                        HttpStatus.OK.name(),
                        "Operation Executed Successfully",
                        response
                )
        );
    }

    protected ResponseEntity<CustomResponse> created(String api, String method, Object response) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CustomResponse(
                        api,
                        method,
                        HttpStatus.CREATED.name(),
                        "Resource Created Successfully",
                        response
                )
        );
    }
}
