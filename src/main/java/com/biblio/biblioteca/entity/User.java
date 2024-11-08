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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String kindOfDocument;

    @Column(nullable = false)
    private Integer documentNumber;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;


    @Enumerated(EnumType.STRING)
    private Role role;


    //Relacion entre el usuario y sus reservas
    @OneToMany(targetEntity = Reservation.class, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    @OneToMany(targetEntity = Loan.class, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Loan> loans;




}
