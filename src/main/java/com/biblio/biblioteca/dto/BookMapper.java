package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Author;
import com.biblio.biblioteca.entity.Book;
import com.biblio.biblioteca.service.AuthorService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.id", target = "author")
    BookDTO toDTO(Book book);

    @Mapping(source = "author.id", target="author")
    @Mapping(target = "id", ignore = true)
    BookDTO toDTOWithoutId(Book book);

    @Mapping(source = "author", target = "author", qualifiedByName = "IdToAuthor")
    Book toEntity(BookDTO bookDTO, @Context AuthorService authorService);

    @Named("IdToAuthor")
    default Author mapIdToAuthor(Long id, @Context AuthorService authorService) {
        return id != null ?authorService.findAuthorById(id) : null;
    }
}
