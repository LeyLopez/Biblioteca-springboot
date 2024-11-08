package com.biblio.biblioteca.api;


import com.biblio.biblioteca.dto.LoanDTO;
import com.biblio.biblioteca.exception.NotFoundException;
import com.biblio.biblioteca.service.LoanService;
import org.springframework.http.ResponseEntity;
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


    @GetMapping
    public ResponseEntity<List<LoanDTO>> getLoans() {
        return ResponseEntity.ok(prestamoService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable("id") Long id) {
        return prestamoService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElseThrow(()->new NotFoundException("No se encontró el préstamo con el ID "+id));
    }


    @PostMapping
    public ResponseEntity<LoanDTO> createdLoan(@RequestBody LoanDTO prestamo) {
        return createLoan(prestamo);
    }

    private ResponseEntity<LoanDTO> createLoan(LoanDTO prestamo) {
        LoanDTO newPrestamo = prestamoService.save(prestamo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newPrestamo.id()).toUri();
        return ResponseEntity.created(location).body(newPrestamo);
    }


    @PutMapping("/id")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long id, @RequestBody LoanDTO prestamo) {
        Optional<LoanDTO> prestamoToUpdate = prestamoService.update(id, prestamo);
        return prestamoToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->createLoan(prestamo));
    }


    @DeleteMapping
    public ResponseEntity<LoanDTO> deleteLoan(@PathVariable Long id) {
        return prestamoService.findById(id)
                .map(l->{
                    prestamoService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElseThrow(()->new NotFoundException("No se encontró el préstamo con el ID "+id));
    }
}
