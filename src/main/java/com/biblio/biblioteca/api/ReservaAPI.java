package com.biblio.biblioteca.api;


import com.biblio.biblioteca.dto.ReservaDTO;
import com.biblio.biblioteca.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserva")
public class ReservaAPI {

    private final ReservaService reservaService;


    public ReservaAPI(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> obtenerReservas() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<ReservaDTO> obtenerReservasId(@PathVariable("id") Long id) {
        return reservaService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ReservaDTO> crearReservas(@RequestBody ReservaDTO reserva) {
        return createReserva(reserva);
    }

    private ResponseEntity<ReservaDTO> createReserva(ReservaDTO reserva) {
        ReservaDTO newReserva = reservaService.save(reserva);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newReserva.id()).toUri();
        return ResponseEntity.created(location).body(newReserva);
    }


    @PutMapping("/id")
    public ResponseEntity<ReservaDTO> actualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reserva) {
        Optional<ReservaDTO> reservaToUpdate = reservaService.update(id, reserva);
        return reservaToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @DeleteMapping
    public ResponseEntity<ReservaDTO> eliminarReserva(@PathVariable Long id) {
        return reservaService.findById(id)
                .map(l->{
                    reservaService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElse(ResponseEntity.notFound().build());
    }
}
