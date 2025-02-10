package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDTO(Genre genre);

    @Mapping(target = "id", ignore = true)
    GenreDTO toDTOWithoutId(Genre genre);

    Genre toEntity(GenreDTO genreDTO);
}
