package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Estado;
import com.biblio.biblioteca.entity.Libro;
import com.biblio.biblioteca.entity.Usuario;

import java.util.Date;

public record PrestamoDTO(Long id,
                          Date fechaPrestamo,
                          Date fechaDevolucion,
                          Date fechaCambioEstado,
                          Usuario usuario,
                          Libro libro,
                          Estado estado) {
}
