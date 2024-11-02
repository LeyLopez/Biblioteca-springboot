package com.biblio.biblioteca.service;


import com.biblio.biblioteca.dto.ReservaDTO;

import java.util.List;
import java.util.Optional;

public interface ReservaService {

    Optional<ReservaDTO> findById(Long id);

    ReservaDTO save(ReservaDTO reservaDTO);

    void delete(Long id);

    Optional<ReservaDTO> update(Long id, ReservaDTO reservaDTO);

    List<ReservaDTO> findAll();
}
