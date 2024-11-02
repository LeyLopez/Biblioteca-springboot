package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.LibroDTO;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    Optional<LibroDTO> findById(Long id);

    Optional<LibroDTO> findByName(String name);

    LibroDTO save(LibroDTO libroDTO);

    void delete(Long id);

    Optional<LibroDTO> update(Long id, LibroDTO libroDTO);

    List<LibroDTO> findAll();

}
