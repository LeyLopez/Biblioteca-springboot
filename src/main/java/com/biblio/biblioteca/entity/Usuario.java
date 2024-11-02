package com.biblio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false)
    private String tipoDocumento;

    @Column(nullable = false)
    private Integer numeroDocumento;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;


    @Enumerated(EnumType.STRING)
    private Rol rol;


    //Relacion entre el usuario y sus reservas
    @OneToMany(targetEntity = Reserva.class, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Reserva> reservas;

    @OneToMany(targetEntity = Prestamo.class, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Prestamo> prestamos;




}
