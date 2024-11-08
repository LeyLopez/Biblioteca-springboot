package com.biblio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservas")
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationEndDate;


    @Column(nullable = false)
    private Date statusChangeDate;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Book.class)
    private Book book;

    @Enumerated(EnumType.STRING)
    private Status status;


}
