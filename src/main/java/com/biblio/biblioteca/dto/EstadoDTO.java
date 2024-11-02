package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Prestamo;
import com.biblio.biblioteca.entity.Reserva;

import java.util.Set;

public record EstadoDTO(Long id,
                        String nombre,
                        String descripcion,
                        Set<Prestamo> prestamos,
                        Set<Reserva> reservas) {

}
