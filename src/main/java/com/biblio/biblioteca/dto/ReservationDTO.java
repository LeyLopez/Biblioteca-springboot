package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Status;
import com.biblio.biblioteca.entity.Book;
import com.biblio.biblioteca.entity.User;

import java.util.Date;

public record ReservationDTO(Long id,
                             Date reservationDate,
                             Date ReservationEndDate,
                             Date statusChangeDate,
                             Long user,
                             Long book,
                             Status status) {
}
