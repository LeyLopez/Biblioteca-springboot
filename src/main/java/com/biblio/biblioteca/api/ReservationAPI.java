package com.biblio.biblioteca.api;


import com.biblio.biblioteca.dto.ReservationDTO;
import com.biblio.biblioteca.exception.NotFoundException;
import com.biblio.biblioteca.security.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Obtener todos las reservas.", description = "Retorna una lista con todas las reservas de la biblioteca.")
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @Operation(summary = "Obtener un reserva por ID.", description = "Retorna el objeto reserva con el ID proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Long id) {
        return reservationService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElseThrow(()->new NotFoundException("No se encontró la reserva con el ID "+id));
    }

    @Operation(summary = "Crear una reserva sino existe, si existe, la reserva se actualizará con los nuevos datos.",
            description = "Retorna el objeto reserva creado.")
    @PostMapping
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<ReservationDTO> createdReservation(@RequestBody ReservationDTO reserva) {
        return createReservation(reserva);
    }

    private ResponseEntity<ReservationDTO> createReservation(ReservationDTO reserva) {
        ReservationDTO newReserva = reservationService.save(reserva);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newReserva.id()).toUri();
        return ResponseEntity.created(location).body(newReserva);
    }

    @Operation(summary = "Actualizar una reserva por ID.",  description = "Retorna el objeto reserva con los datos actualizados.")
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        Optional<ReservationDTO> reservaToUpdate = reservationService.update(id, reservation);
        return reservaToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->createReservation(reservation));
    }

    @Operation(summary = "Eliminar reserva por ID.", description = "Retorna el objeto reserva eliminado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDTO> deleteReservation(@PathVariable Long id) {
        return reservationService.findById(id)
                .map(l->{
                    reservationService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElseThrow(()->new NotFoundException("No se encontró la reserva con el ID "+id));
    }
}
