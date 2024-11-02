package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Estado;
import com.biblio.biblioteca.entity.Libro;
import com.biblio.biblioteca.entity.Usuario;

import java.util.Date;

public record ReservaDTO(Long id,
                         Date fechaReserva,
                         Date fechaVencimientoReserva,
                         Date fechaCambioEstado,
                         Usuario usuario,
                         Libro libro,
                         Estado estado) {
}
