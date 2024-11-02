package com.biblio.biblioteca.service;



import com.biblio.biblioteca.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioDTO> findById(Long id);

    Optional<UsuarioDTO> findByEmail(String email);

    Optional<UsuarioDTO> findByNumeroDocumento(Integer numeroDocumento);

    Optional<UsuarioDTO> findByName(String name);

    UsuarioDTO save(UsuarioDTO usuarioDTO);

    void delete(Long id);

    Optional<UsuarioDTO> update(Long id, UsuarioDTO usuarioDTO);

    List<UsuarioDTO> findAll();

}
