package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookMapper {

    BookDTO toDTO(Book book);

    @Mapping(target = "id", ignore = true)
    BookDTO toDTOWithoutId(Book book);

    Book toEntity(BookDTO bookDTO);
}
