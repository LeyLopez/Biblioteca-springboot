package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Loan;
import com.biblio.biblioteca.entity.Reservation;
import com.biblio.biblioteca.entity.Role;

import java.util.Date;
import java.util.Set;

public record UserDTO(Long id, String name,
                      String lastname,
                      String email,
                      String password,
                      String documentType,
                      Integer documentNumber,
                      Date dateOfBirth,
                      String phoneNumber,
                      String address,
                      Role role
                      ) {
}
