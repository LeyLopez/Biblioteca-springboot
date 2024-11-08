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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date dateOfPublication;

    @Column(nullable = false)
    private Integer quantity;

    //Relación entre los libros y el autor
    @ManyToOne(targetEntity = Author.class)
    private Author author;

    //Relacion entre el libro y sus reservas
    @OneToMany(targetEntity = Reservation.class, mappedBy = "libro", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @OneToMany(targetEntity = Loan.class, mappedBy = "libro", fetch = FetchType.LAZY)
    private Set<Loan> loans;


    @ManyToMany
    @JoinTable(name = "GENEROS_LIBRO",
    joinColumns = @JoinColumn(name = "LIBRO_ID", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "GENERO_ID", referencedColumnName = "id"))
    private Set<Genre> genres;


}
