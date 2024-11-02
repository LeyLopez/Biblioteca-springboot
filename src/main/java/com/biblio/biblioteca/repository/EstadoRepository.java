package com.biblio.biblioteca.repository;

import com.biblio.biblioteca.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findByName(String name);
}
