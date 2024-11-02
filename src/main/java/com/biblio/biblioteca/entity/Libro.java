package com.biblio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "libros")
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String titulo;


    @Column(nullable = false)
    private String resumen;

    @Column(nullable = false)
    private Date fechaPublicacion;

    @Column(nullable = false)
    private Integer cantidadEjemplares;

    //Relación entre los libros y el autor
    @ManyToOne(targetEntity = Autor.class)
    private Autor autor;

    //Relacion entre el libro y sus reservas
    @OneToMany(targetEntity = Reserva.class, mappedBy = "libro", fetch = FetchType.LAZY)
    private Set<Reserva> reservas;

    @OneToMany(targetEntity = Prestamo.class, mappedBy = "libro", fetch = FetchType.LAZY)
    private Set<Prestamo> prestamos;


    @ManyToMany
    @JoinTable(name = "GENEROS_LIBRO",
    joinColumns = @JoinColumn(name = "LIBRO_ID", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "GENERO_ID", referencedColumnName = "id"))
    private Set<Genero> generos;


}
