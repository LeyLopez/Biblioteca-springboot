package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReservationMapper {

    ReservationDTO toDTO(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    ReservationDTO toDTOWithoutId(Reservation reservation);

    Reservation toEntity(ReservationDTO reservationDTO);

}
