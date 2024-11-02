package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Libro;

import java.util.Set;

public record GeneroDTO(Long id,
                        String nombre,
                        Set<Libro> libros) {
}
