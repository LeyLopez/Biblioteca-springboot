package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.AutorDTO;


import java.util.List;
import java.util.Optional;

public interface AutorService {

    Optional<AutorDTO> findById(Long id);

    Optional<AutorDTO> findByName(String name);

    AutorDTO save(AutorDTO autorDTO);

    void delete(Long id);

    Optional<AutorDTO> update(Long id, AutorDTO autorDTO);

    List<AutorDTO> findAll();
}
