package com.biblio.biblioteca.dto;

import java.util.Date;

public record UserDTO(Long id, String name,
                      String lastname,
                      String email,
                      String password,
                      String documentType,
                      Integer documentNumber,
                      Date dateOfBirth,
                      String phoneNumber,
                      String address
                      ) {
}
