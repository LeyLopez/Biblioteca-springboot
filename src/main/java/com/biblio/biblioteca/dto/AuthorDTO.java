package com.biblio.biblioteca.dto;

import java.util.Date;

public record AuthorDTO(Long id,
                        String name,
                        String lastname,
                        Date dateOfBirth
                       ) {
}
