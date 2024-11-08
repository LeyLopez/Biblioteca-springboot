package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    UserDTO toDTO(User user);

    @Mapping(target="id", ignore = true)
    UserDTO toDTOWithoutId(User user);

    User toEntity(UserDTO userDTO);

}
