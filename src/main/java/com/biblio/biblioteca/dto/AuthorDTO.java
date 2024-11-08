package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Book;

import java.util.Date;
import java.util.Set;

public record AuthorDTO(Long id,
                        String name,
                        String lastname,
                        Date dateOfBirth
                       ) {
}
