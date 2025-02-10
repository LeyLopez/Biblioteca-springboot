package com.biblio.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(nullable = false)
    private String coverPage;

    //Relación entre los libros y el autor
    @ManyToOne(targetEntity = Author.class)
    private Author author;

    //Relacion entre el libro y sus reservas
    @JsonIgnore
    @OneToMany(targetEntity = Reservation.class, mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @JsonIgnore
    @OneToMany(targetEntity = Loan.class, mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Loan> loans;


    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "book_genres",
    joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private Set<Genre> genres;


}
