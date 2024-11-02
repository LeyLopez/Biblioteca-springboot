package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Libro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LibroMapper {

    LibroDTO toDTO(Libro libro);

    @Mapping(target = "id", ignore = true)
    LibroDTO toDTOWithoutId(Libro libro);

    Libro toEntity(LibroDTO libroDTO);
}
