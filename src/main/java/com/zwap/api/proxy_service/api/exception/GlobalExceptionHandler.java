package com.zwap.api.proxy_service.api.exception;

import com.zwap.api.proxy_service.api.exception.common.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(@NotNull NotFoundException e) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(@NotNull UnauthorizedException e) {
        return buildResponseEntity(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(@NotNull ForbiddenException e) {
        return buildResponseEntity(HttpStatus.FORBIDDEN, e.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(@NotNull BadRequestException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflictException(@NotNull ConflictException e) {
        return buildResponseEntity(HttpStatus.CONFLICT, e.getMessage());
    }
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<?> handleUnprocessableEntityException(@NotNull UnprocessableEntityException e) {
        return buildResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
    }
    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<?> handleTooManyRequestsException(@NotNull TooManyRequestsException e) {
        return buildResponseEntity(HttpStatus.TOO_MANY_REQUESTS, e.getMessage());
    }
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleServiceException(@NotNull ServiceException e) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(@NotNull BusinessException e) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity<?> handleGatewayTimeoutException(@NotNull GatewayTimeoutException e) {
        return buildResponseEntity(HttpStatus.GATEWAY_TIMEOUT, e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(@NotNull Exception e) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
    @NotNull
    private ResponseEntity<?> buildResponseEntity(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(message);
    }
}
