package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Book;
import com.biblio.biblioteca.entity.Reservation;
import com.biblio.biblioteca.entity.User;
import com.biblio.biblioteca.service.BookService;
import com.biblio.biblioteca.service.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "user.id", target = "user")
    @Mapping(source = "book.id", target = "book")
    ReservationDTO toDTO(Reservation reservation);

    @Mapping(source = "book.id", target = "book")
    @Mapping(source = "user.id", target = "user")
    @Mapping(target = "id", ignore = true)
    ReservationDTO toDTOWithoutId(Reservation reservation);

    @Mapping(source = "book", target = "book", qualifiedByName = "IdToBook")
    @Mapping(source = "user", target = "user", qualifiedByName = "IdToUser")
    Reservation toEntity(ReservationDTO reservationDTO, @Context UserService userService, @Context BookService bookService);

    @Named("IdToUser")
    default User mapIdToUser(Long id, @Context UserService userService) {
        return id != null ? userService.findUserById(id) : null;
    }

    @Named("IdToBook")
    default Book mapIdToBook(Long id, @Context BookService bookService) {
        return id!=null? bookService.findBookById(id) :null;
    }
}
