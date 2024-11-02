package com.biblio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estados")
@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(targetEntity = Prestamo.class, mappedBy = "estado", fetch = FetchType.LAZY)
    private Set<Prestamo> prestamos;

    @OneToMany(targetEntity = Reserva.class, mappedBy = "estado", fetch = FetchType.LAZY)
    private Set<Reserva> reservas;

}
