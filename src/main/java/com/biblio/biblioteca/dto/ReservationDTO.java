package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Status;

import java.util.Date;

public record ReservationDTO(Long id,
                             Date reservationDate,
                             Date reservationEndDate,
                             Date statusChangeDate,
                             Long user,
                             Long book,
                             Status status) {
}
