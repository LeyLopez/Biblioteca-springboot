package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Prestamo;
import com.biblio.biblioteca.entity.Reserva;
import com.biblio.biblioteca.entity.Rol;

import java.util.Date;
import java.util.Set;

public record UsuarioDTO(Long id, String nombre,
                         String apellido,
                         String email,
                         String clave,
                         String tipoDocumento,
                         Integer numeroDocumento,
                         Date fechNacimiento,
                         String telefono,
                         String direccion,
                         Rol rol,
                         Set<Reserva> reservas,
                         Set<Prestamo> prestamos) {
}
