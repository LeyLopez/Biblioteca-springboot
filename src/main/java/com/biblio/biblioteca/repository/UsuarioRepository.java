package com.biblio.biblioteca.repository;

import com.biblio.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByName(String name);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNumeroDocumento(Integer numeroDocumento);

}
