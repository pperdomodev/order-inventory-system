package com.inventory.order.test.infrastructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inventory.order.test.domain.exception.BusinessException;
import com.inventory.order.test.shared.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse>
    handleBusinessException(
            BusinessException ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage(),
                        "BUSINESS_ERROR");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGenericException(
            Exception ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage(),
                        "INTERNAL_ERROR");

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}