package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Genero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GeneroMapper {

    GeneroDTO toDTO(Genero genero);

    @Mapping(target = "id", ignore = true)
    GeneroDTO toDTOWithoutId(Genero genero);

    Genero toEntity(GeneroDTO generoDTO);
}
