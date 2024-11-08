package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.AuthorDTO;


import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<AuthorDTO> findById(Long id);

    Optional<AuthorDTO> findByName(String name);

    AuthorDTO save(AuthorDTO authorDTO);

    void delete(Long id);

    Optional<AuthorDTO> update(Long id, AuthorDTO authorDTO);

    List<AuthorDTO> findAll();
}
