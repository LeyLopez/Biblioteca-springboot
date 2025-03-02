package com.biblio.biblioteca.dto;

import java.util.Date;

public record SignupRequest(
        Long id,
        String name,
        String lastname,
        String email,
        String username,
        String password,
        String kindOfDocument,
        Integer documentNumber,
        Date dateOfBirth,
        String phoneNumber,
        String address
) {
}
