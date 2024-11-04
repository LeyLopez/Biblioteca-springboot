package com.biblio.biblioteca.api;


import com.biblio.biblioteca.dto.PrestamoDTO;
import com.biblio.biblioteca.service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestamo")
public class PrestamoAPI {

    private final PrestamoService prestamoService;

    public PrestamoAPI(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }


    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> obtenerPrestamos() {
        return ResponseEntity.ok(prestamoService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<PrestamoDTO> obtenerPrestamoId(@PathVariable("id") Long id) {
        return prestamoService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<PrestamoDTO> crearPrestamo(@RequestBody PrestamoDTO prestamo) {
        return createPrestamo(prestamo);
    }

    private ResponseEntity<PrestamoDTO> createPrestamo(PrestamoDTO prestamo) {
        PrestamoDTO newPrestamo = prestamoService.save(prestamo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newPrestamo.id()).toUri();
        return ResponseEntity.created(location).body(newPrestamo);
    }


    @PutMapping("/id")
    public ResponseEntity<PrestamoDTO> actualizarPrestamo(@PathVariable Long id, @RequestBody PrestamoDTO prestamo) {
        Optional<PrestamoDTO> prestamoToUpdate = prestamoService.update(id, prestamo);
        return prestamoToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @DeleteMapping
    public ResponseEntity<PrestamoDTO> eliminarPrestamo(@PathVariable Long id) {
        return prestamoService.findById(id)
                .map(l->{
                    prestamoService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElse(ResponseEntity.notFound().build());
    }
}
