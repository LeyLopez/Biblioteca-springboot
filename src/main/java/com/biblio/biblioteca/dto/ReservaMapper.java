package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReservaMapper {

    ReservaDTO toDTO(Reserva reserva);

    @Mapping(target = "id", ignore = true)
    ReservaDTO toDTOWithoutId(Reserva reserva);

    Reserva toEntity(ReservaDTO reservaDTO);

}
