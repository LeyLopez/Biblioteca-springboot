package com.biblio.biblioteca.api;


import com.biblio.biblioteca.dto.ReservationDTO;
import com.biblio.biblioteca.exception.NotFoundException;
import com.biblio.biblioteca.security.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserva")
public class ReservationAPI {

    private final ReservationService reservationService;


    public ReservationAPI(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Long id) {
        return reservationService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElseThrow(()->new NotFoundException("No se encontró la reserva con el ID "+id));
    }


    @PostMapping
    public ResponseEntity<ReservationDTO> createdReservation(@RequestBody ReservationDTO reserva) {
        return createReservation(reserva);
    }

    private ResponseEntity<ReservationDTO> createReservation(ReservationDTO reserva) {
        ReservationDTO newReserva = reservationService.save(reserva);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newReserva.id()).toUri();
        return ResponseEntity.created(location).body(newReserva);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        Optional<ReservationDTO> reservaToUpdate = reservationService.update(id, reservation);
        return reservaToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->createReservation(reservation));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDTO> deleteReservation(@PathVariable Long id) {
        return reservationService.findById(id)
                .map(l->{
                    reservationService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElseThrow(()->new NotFoundException("No se encontró la reserva con el ID "+id));
    }
}
