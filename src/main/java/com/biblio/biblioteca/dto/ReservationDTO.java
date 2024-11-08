package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Status;
import com.biblio.biblioteca.entity.Book;
import com.biblio.biblioteca.entity.User;

import java.util.Date;

public record ReservationDTO(Long id,
                             Date reservationDate,
                             Date ReservationEndDate,
                             Date statusChangeDate,
                             User user,
                             Book book,
                             Status status) {
}
