package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EstadoMapper {

    EstadoDTO toDTO(Estado estado);

    @Mapping(target = "id", ignore = true)
    EstadoDTO toDTOWithoutID(Estado estado);

    Estado toEntity(EstadoDTO estadoDTO);

}
