package com.biblio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.bcel.classfile.EnumElementValue;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservas")
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaReserva;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaVencimientoReserva;


    @Column(nullable = false)
    private Date fechaCambioEstado;

    @ManyToOne(targetEntity = Usuario.class)
    private Usuario usuario;

    @ManyToOne(targetEntity = Libro.class)
    private Libro libro;

    @Enumerated(EnumType.STRING)
    private Estado estado;


}
