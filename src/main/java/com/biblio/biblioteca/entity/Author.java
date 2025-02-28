package com.biblio.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

//Clase que representa a la entidad autor, aquí se definen los atributos de la tabla autores,
// entre los cuales se encuentran el nombre el apellido y la fecha de nacimiento del autor.
//Además, se presenta la relacion con la tabla libros.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "autores")
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @JsonIgnore
    @OneToMany(targetEntity = Book.class, mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Book> books;


}
