package com.biblio.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorMessage> NotFoundHandler(NotFoundException ex, WebRequest request) {
        ErrorMessage err = new ErrorMessage();
        err.setMessage(ex.getMessage());
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
