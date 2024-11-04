package com.biblio.biblioteca.api;

import com.biblio.biblioteca.dto.LibroDTO;
import com.biblio.biblioteca.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libro")
public class LibroAPI {

    private final LibroService libroService;

    public LibroAPI(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<LibroDTO>> obtenerLibros() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<LibroDTO> obtenerLibroId(@PathVariable ("id") Long id) {
        return libroService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<LibroDTO> crearLibro(@RequestBody LibroDTO libro) {
        return createLibro(libro);
    }

    private ResponseEntity<LibroDTO> createLibro(LibroDTO libro) {
        LibroDTO newLibro = libroService.save(libro);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newLibro.id()).toUri();
        return ResponseEntity.created(location).body(newLibro);
    }


    @PutMapping("/id")
    public ResponseEntity<LibroDTO> actualizarLibro(@PathVariable Long id, @RequestBody LibroDTO libro) {
        Optional<LibroDTO> libroToUpdate = libroService.update(id, libro);
        return libroToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @DeleteMapping
    public ResponseEntity<LibroDTO> eliminarLibro(@PathVariable Long id) {
        return libroService.findById(id)
                .map(l->{
                    libroService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElse(ResponseEntity.notFound().build());
    }


}
