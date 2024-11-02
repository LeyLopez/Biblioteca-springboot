package com.biblio.biblioteca.service;



import com.biblio.biblioteca.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioDTO> findById(Long id);

    Optional<UsuarioDTO> findByName(String name);

    Optional<UsuarioDTO> save(UsuarioDTO usuarioDTO);

    Optional<UsuarioDTO> delete(Long id);

    Optional<UsuarioDTO> update(Long id, UsuarioDTO usuarioDTO);

    List<UsuarioDTO> findAll();

}
