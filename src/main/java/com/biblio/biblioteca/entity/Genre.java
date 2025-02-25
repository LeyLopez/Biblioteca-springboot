package com.biblio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
//Clase que representa a la entidad generos, aquí se definen los atributos de la tabla generos,
// entre los cuales se encuentran el id y el nombre del genero.
//. Además, se presenta la relacion con la tabla de libros.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "generos")
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;
}
