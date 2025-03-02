package com.biblio.biblioteca.api;

import com.biblio.biblioteca.dto.BookDTO;
import com.biblio.biblioteca.exception.NotFoundException;
import com.biblio.biblioteca.security.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libro")
public class BookAPI {

    private final BookService bookService;

    public BookAPI(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Obtener todos los libros.", description = "Retorna una lista con todos libros de la biblioteca.")
    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Operation(summary = "Obtener un libro por ID.", description = "Retorna el objeto libro con el ID proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable ("id") Long id) {
        return bookService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElseThrow(()->new NotFoundException("No se encontró el libro con el ID "+id));
    }


    @Operation(summary = "Crear un libro sino existe, si existe, el libro se actualizará con los nuevos datos.",
            description = "Retorna el objeto libro creado.")
    @PostMapping
    public ResponseEntity<BookDTO> createdBook(@RequestBody BookDTO libro) {
        return createBook(libro);
    }

    private ResponseEntity<BookDTO> createBook(BookDTO libro) {
        BookDTO newLibro = bookService.save(libro);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newLibro.id()).toUri();
        return ResponseEntity.created(location).body(newLibro);
    }

    @Operation(summary = "Actualizar un libro por ID.",  description = "Retorna el objeto libro con los datos actualizados.")
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO book) {
        Optional<BookDTO> libroToUpdate = bookService.update(id, book);
        return libroToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->createdBook(book));
    }

    @Operation(summary = "Eliminar libro por ID.", description = "Retorna el objeto libro eliminado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        return bookService.findById(id)
                .map(l->{
                    bookService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElseThrow(()->new NotFoundException("No se encontró el libro con el ID "+id));
    }


}
