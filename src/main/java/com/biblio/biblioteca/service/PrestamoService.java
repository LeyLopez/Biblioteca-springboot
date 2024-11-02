package com.biblio.biblioteca.service;



import com.biblio.biblioteca.dto.PrestamoDTO;

import java.util.List;
import java.util.Optional;

public interface PrestamoService {

    Optional<PrestamoDTO> findById(Long id);

    Optional<PrestamoDTO> findByName(String name);

    Optional<PrestamoDTO> save(PrestamoDTO prestamoDTO);

    Optional<PrestamoDTO> delete(Long id);

    Optional<PrestamoDTO> update(Long id, PrestamoDTO prestamoDTO);

    List<PrestamoDTO> findAll();
}
