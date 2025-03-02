package com.biblio.biblioteca.api;


import com.biblio.biblioteca.dto.LoanDTO;
import com.biblio.biblioteca.exception.NotFoundException;
import com.biblio.biblioteca.security.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestamo")
public class LoanAPI {

    private final LoanService prestamoService;

    public LoanAPI(LoanService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @Operation(summary = "Obtener todos los préstamos.", description = "Retorna una lista con todos préstamos de la biblioteca.")
    @GetMapping
    public ResponseEntity<List<LoanDTO>> getLoans() {
        return ResponseEntity.ok(prestamoService.findAll());
    }

    @Operation(summary = "Obtener un préstamo por ID.", description = "Retorna el objeto préstamo con el ID proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable("id") Long id) {
        return prestamoService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElseThrow(()->new NotFoundException("No se encontró el préstamo con el ID "+id));
    }

    @Operation(summary = "Crear un préstamo sino existe, si existe, el préstamo se actualizará con los nuevos datos.",
            description = "Retorna el objeto préstamo creado.")
    @PostMapping
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<LoanDTO> createdLoan(@RequestBody LoanDTO prestamo) {
        return createLoan(prestamo);
    }


    private ResponseEntity<LoanDTO> createLoan(LoanDTO prestamo) {
        LoanDTO newPrestamo = prestamoService.save(prestamo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newPrestamo.id()).toUri();
        return ResponseEntity.created(location).body(newPrestamo);
    }

    @Operation(summary = "Actualizar un préstamo por ID.",  description = "Retorna el objeto préstamo con los datos actualizados.")
    @PutMapping("/{id}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long id, @RequestBody LoanDTO prestamo) {
        Optional<LoanDTO> prestamoToUpdate = prestamoService.update(id, prestamo);
        return prestamoToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->createLoan(prestamo));
    }

    @Operation(summary = "Eliminar préstamo por ID.", description = "Retorna el objeto préstamo eliminado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<LoanDTO> deleteLoan(@PathVariable Long id) {
        return prestamoService.findById(id)
                .map(l->{
                    prestamoService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElseThrow(()->new NotFoundException("No se encontró el préstamo con el ID "+id));
    }
}
