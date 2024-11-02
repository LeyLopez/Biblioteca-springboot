package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.EstadoDTO;

import java.util.List;
import java.util.Optional;

public interface EstadoService {

    Optional<EstadoDTO> findById(Long id);

    Optional<EstadoDTO> findByName(Long id);

    EstadoDTO save(EstadoDTO estadoDTO);

    Optional<EstadoDTO> delete(Long id);

    Optional<EstadoDTO> update(Long id, EstadoDTO estadoDTO);

    List<EstadoDTO> findAll();

}
