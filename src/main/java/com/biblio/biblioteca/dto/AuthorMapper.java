package com.biblio.biblioteca.dto;


import com.biblio.biblioteca.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO toDto(Author author);

    @Mapping(target = "id", ignore = true)
    AuthorDTO toDTOWithoutId(Author author);

    Author toEntity(AuthorDTO authorDTO);
}
