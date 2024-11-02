package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.GeneroDTO;

import java.util.List;
import java.util.Optional;

public interface GeneroService {

    Optional<GeneroDTO> findById(Long ig);

    Optional<GeneroDTO> findByName(String name);

    Optional<GeneroDTO> save(GeneroDTO generoDTO);

    Optional<GeneroDTO> delete(Long id);

    Optional<GeneroDTO> update(Long id, GeneroDTO generoDTO);

    List<GeneroDTO> findAll();


}
