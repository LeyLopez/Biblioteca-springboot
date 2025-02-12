package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Book;
import com.biblio.biblioteca.entity.Loan;
import com.biblio.biblioteca.entity.User;
import com.biblio.biblioteca.service.BookService;
import com.biblio.biblioteca.service.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(source = "book.id", target = "book")
    @Mapping(source = "user.id", target = "user")
    LoanDTO toDTO(Loan loan);

    @Mapping(source = "book.id", target = "book")
    @Mapping(source = "user.id", target = "user")
    @Mapping(target="id", ignore = true)
    LoanDTO toDTOWithoutId(Loan loan);

    @Mapping(source = "user", target = "user", qualifiedByName = "IdToUser")
    @Mapping(source = "book", target = "book", qualifiedByName = "IdToBook")
    Loan toEntity(LoanDTO loanDTO, @Context UserService userService, @Context BookService bookService);

    @Named("IdToUser")
    default User mapIdToUser(Long id, @Context UserService userService) {
        return id != null ? userService.findUserById(id) : null;
    }

    @Named("IdToBook")
    default Book mapIdToBook(Long id, @Context BookService bookService) {
        return id!=null ? bookService.findBookById(id):null;
    }
}
