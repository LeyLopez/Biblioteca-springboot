package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target="id", ignore = true)
    UsuarioDTO toDTOWithoutId(Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);

}
