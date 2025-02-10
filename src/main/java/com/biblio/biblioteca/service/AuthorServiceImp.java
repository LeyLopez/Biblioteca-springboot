package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.AuthorDTO;
import com.biblio.biblioteca.dto.AuthorMapper;
import com.biblio.biblioteca.entity.Author;
import com.biblio.biblioteca.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImp(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public Optional<AuthorDTO> findById(Long id) {
        return authorRepository.findById(id).map(authorMapper::toDto);
    }

    @Override
    public Optional<AuthorDTO> findByName(String name) {
        return authorRepository.findByName(name).map(authorMapper::toDto);
    }

    @Override
    public AuthorDTO save(AuthorDTO authorDTO) {
        Author author = authorRepository.save(authorMapper.toEntity(authorDTO));
        return authorMapper.toDto(author);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Optional<AuthorDTO> update(Long id, AuthorDTO authorDTO) {
        return authorRepository.findById(id)
                .map(autorInBD->{
                    autorInBD.setName(authorDTO.name());
                    autorInBD.setLastname(authorDTO.lastname());
                    autorInBD.setDateOfBirth(authorDTO.dateOfBirth());


                    return authorRepository.save(autorInBD);

        }).map(authorMapper::toDto);
    }

    @Override
    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream()
                .map(dto-> authorMapper.toDto(dto))
                .collect(Collectors.toList());
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
}
