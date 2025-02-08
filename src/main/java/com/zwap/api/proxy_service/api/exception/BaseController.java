package com.zwap.api.proxy_service.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.function.Supplier;

@ControllerAdvice
public class BaseController {
    protected <T> ResponseEntity<T> handleRequest(Supplier<T> supplier) {
        return ResponseEntity.ok(supplier.get());
    }
}
