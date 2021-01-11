package com.virtualpairprogrammers.roombooking.rest;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Value
@ToString
@EqualsAndHashCode
public class ErrorResponse {

    int status;
    String error;
    String message;

    public static ResponseEntity<ErrorResponse> create(final HttpStatus status, final Throwable ex, final String message) {
        final String error = ex.getClass().getSimpleName();
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), error, message == null ? error : message));
    }
}
