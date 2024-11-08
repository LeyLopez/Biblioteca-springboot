package com.biblio.biblioteca.api;

import com.biblio.biblioteca.dto.GenreDTO;
import com.biblio.biblioteca.exception.NotFoundException;
import com.biblio.biblioteca.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genero")
public class GenreAPI {

    private final GenreService generoService;


    public GenreAPI(GenreService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenres(){
        return ResponseEntity.ok(generoService.findAll());
    }


    @GetMapping("/id")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable("id") Long id){
        return generoService.findById(id).map(g->ResponseEntity.ok().body(g))
                .orElseThrow(()->new NotFoundException("No se encontró el genero con el ID "+id));
    }


    @PostMapping
    public ResponseEntity<GenreDTO> createdGenre(@RequestBody GenreDTO genero){
        return createGenre(genero);
    }

    private ResponseEntity<GenreDTO> createGenre(GenreDTO genero) {
        GenreDTO newGenero = generoService.save(genero);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newGenero.id()).toUri();

        return ResponseEntity.created(location).body(newGenero);
    }

    @PutMapping("/id")
    public ResponseEntity<GenreDTO> actualizarGenero(@PathVariable Long id, @RequestBody GenreDTO genero){
        Optional<GenreDTO> generoToUpdate = generoService.update(id, genero);
        return generoToUpdate.map(g->ResponseEntity.ok(g))
                .orElseGet(()->{
                    return createGenre(genero);
                });
    }

    @DeleteMapping
    public ResponseEntity<GenreDTO> eliminarGenero(@PathVariable Long id){
        return generoService.findById(id).map(g->{
            generoService.delete(id);
            return ResponseEntity.ok().body(g);
        }).orElseThrow(()->new NotFoundException("No se encontró el genero con el ID "+id));
    }


}
