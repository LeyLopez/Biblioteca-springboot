package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Libro;

import java.util.Date;
import java.util.Set;

public record AutorDTO(Long id,
                       String nombre,
                       String apellido,
                       Date fechaNacimiento,
                       Set<Libro> libros) {
}
