package com.poc.microservice.demo.adapters.in.api.rest.commons;

public record CustomResponse(
        String api,
        String method,
        String code,
        String message,
        Object response
) {
}
