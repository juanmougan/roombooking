package com.virtualpairprogrammers.roombooking.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiControllerAdvice {
    private static ResponseEntity<ErrorResponse> errorResponse(final HttpStatus status, final Throwable ex) {
        return errorResponse(status, ex, ex.getMessage());
    }

    private static ResponseEntity<ErrorResponse> errorResponse(final HttpStatus status, final Throwable ex, final String message) {
        log.error(ex.getMessage());
        return ErrorResponse.create(status, ex, message);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> mustExistException(final NotFoundException ex) {
        return errorResponse(HttpStatus.NOT_FOUND, ex);
    }
}
