package com.biblio.biblioteca.api;


import com.biblio.biblioteca.dto.AutorDTO;
import com.biblio.biblioteca.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/autor")
public class AutorAPI {

    private final AutorService autorService;


    public AutorAPI(AutorService autorService) {
        this.autorService = autorService;
    }


    @GetMapping
    public ResponseEntity<List<AutorDTO>> obtenerAutores(){
        return ResponseEntity.ok(autorService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<AutorDTO> obtenerAutorPorId(@PathVariable("id") Long id){
        return autorService.findById(id)
                .map(a->ResponseEntity.ok().body(a))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<AutorDTO> crearAutor(@RequestBody AutorDTO autor){
        return createAutor(autor);
    }

    private ResponseEntity<AutorDTO> createAutor(AutorDTO autor) {
        AutorDTO newAutor = autorService.save(autor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newAutor.id()).toUri();

        return ResponseEntity.created(location).body(newAutor);
    }


    @PutMapping("/id")
    public ResponseEntity<AutorDTO> actualizarAutor(@PathVariable Long id, @RequestBody AutorDTO autor){
        Optional<AutorDTO> autorToUpdate = autorService.update(id, autor);
        return autorToUpdate.map(a->ResponseEntity.ok(a))
                .orElseGet(()-> {
                    return createAutor(autor);
                });
    }

    @DeleteMapping
    public ResponseEntity<AutorDTO> eliminarAutor(@PathVariable Long id){
        return autorService.findById(id).map(a->{
            autorService.delete(id);
            return ResponseEntity.ok().body(a);
        }).orElse(ResponseEntity.notFound().build());
    }
}
