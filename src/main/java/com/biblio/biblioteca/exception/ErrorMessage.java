package com.biblio.biblioteca.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private String message;
    private int status;
    private LocalDateTime timestamp;
}
