package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.GeneroDTO;

import java.util.List;
import java.util.Optional;

public interface GeneroService {

    Optional<GeneroDTO> findById(Long ig);

    Optional<GeneroDTO> findByName(String name);

    GeneroDTO save(GeneroDTO generoDTO);

    void delete(Long id);

    Optional<GeneroDTO> update(Long id, GeneroDTO generoDTO);

    List<GeneroDTO> findAll();


}
