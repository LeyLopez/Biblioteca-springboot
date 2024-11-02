package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Prestamo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PrestamoMapper {

    PrestamoDTO toDTO(Prestamo prestamo);

    @Mapping(target="id", ignore = true)
    PrestamoDTO toDTOWithoutId(Prestamo prestamo);

    Prestamo toEntity(PrestamoDTO prestamoDTO);
}
