package com.biblio.biblioteca.dto;

import com.biblio.biblioteca.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LoanMapper {

    LoanDTO toDTO(Loan loan);

    @Mapping(target="id", ignore = true)
    LoanDTO toDTOWithoutId(Loan loan);

    Loan toEntity(LoanDTO loanDTO);
}
