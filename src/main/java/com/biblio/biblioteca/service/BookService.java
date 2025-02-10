package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.BookDTO;
import com.biblio.biblioteca.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<BookDTO> findById(Long id);

    Optional<BookDTO> findByName(String name);

    BookDTO save(BookDTO bookDTO);

    void delete(Long id);

    Optional<BookDTO> update(Long id, BookDTO bookDTO);

    List<BookDTO> findAll();

    Book findBookById(Long id);
}
