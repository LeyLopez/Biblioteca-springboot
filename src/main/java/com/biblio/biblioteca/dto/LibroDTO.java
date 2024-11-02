package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Autor;
import com.biblio.biblioteca.entity.Genero;
import com.biblio.biblioteca.entity.Prestamo;
import com.biblio.biblioteca.entity.Reserva;

import java.util.Date;
import java.util.Set;

public record LibroDTO(Long id,
                       String titulo,
                       String resumen,
                       Date fechaPublicacion,
                       Integer cantidadEjemplares,
                       Autor autor,
                       Set<Reserva> reservas,
                       Set<Prestamo> prestamos,
                       Set<Genero> generos) {
}
