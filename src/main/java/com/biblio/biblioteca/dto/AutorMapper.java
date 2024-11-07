package com.biblio.biblioteca.dto;


import com.biblio.biblioteca.entity.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorDTO toDto(Autor autor);

    @Mapping(target = "id", ignore = true)
    AutorDTO toDTOWithoutId(Autor autor);

    Autor toEntity(AutorDTO autorDTO);
}
