package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Author;
import com.biblio.biblioteca.entity.Genre;
import com.biblio.biblioteca.entity.Loan;
import com.biblio.biblioteca.entity.Reservation;

import java.util.Date;
import java.util.Set;

public record BookDTO(Long id,
                      String title,
                      String description,
                      Date publicationDate,
                      Integer quantity,
                      Author author

                      ) {
}
