package com.biblio.biblioteca.api;

import com.biblio.biblioteca.dto.GeneroDTO;
import com.biblio.biblioteca.service.GeneroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genero")
public class GeneroAPI {

    private final GeneroService generoService;


    public GeneroAPI(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> obtenerGeneros(){
        return ResponseEntity.ok(generoService.findAll());
    }


    @GetMapping("/id")
    public ResponseEntity<GeneroDTO> obtenerGeneroById(@PathVariable("id") Long id){
        return generoService.findById(id).map(g->ResponseEntity.ok().body(g))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<GeneroDTO> crearGenero(@RequestBody GeneroDTO genero){
        return createGenero(genero);
    }

    private ResponseEntity<GeneroDTO> createGenero(GeneroDTO genero) {
        GeneroDTO newGenero = generoService.save(genero);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newGenero.id()).toUri();

        return ResponseEntity.created(location).body(newGenero);
    }

    @PutMapping("/id")
    public ResponseEntity<GeneroDTO> actualizarGenero(@PathVariable Long id, @RequestBody GeneroDTO genero){
        Optional<GeneroDTO> generoToUpdate = generoService.update(id, genero);
        return generoToUpdate.map(g->ResponseEntity.ok(g))
                .orElseGet(()->{
                    return createGenero(genero);
                });
    }

    @DeleteMapping
    public ResponseEntity<GeneroDTO> eliminarGenero(@PathVariable Long id){
        return generoService.findById(id).map(g->{
            generoService.delete(id);
            return ResponseEntity.ok().body(g);
        }).orElse(ResponseEntity.notFound().build());
    }


}
