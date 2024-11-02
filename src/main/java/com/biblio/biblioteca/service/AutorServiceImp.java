package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.AutorDTO;
import com.biblio.biblioteca.dto.AutorMapper;
import com.biblio.biblioteca.entity.Autor;
import com.biblio.biblioteca.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutorServiceImp implements AutorService{

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;

    public AutorServiceImp(AutorRepository autorRepository, AutorMapper autorMapper) {
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
    }


    @Override
    public Optional<AutorDTO> findById(Long id) {
        return autorRepository.findById(id).map(autorMapper::toDto);
    }

    @Override
    public Optional<AutorDTO> findByName(String name) {
        return autorRepository.findByName(name).map(autorMapper::toDto);
    }

    @Override
    public AutorDTO save(AutorDTO autorDTO) {
        Autor autor = autorRepository.save(autorMapper.toEntity(autorDTO));
        return autorMapper.toDto(autor);
    }

    @Override
    public void delete(Long id) {
        autorRepository.deleteById(id);
    }

    @Override
    public Optional<AutorDTO> update(Long id, AutorDTO autorDTO) {
        return autorRepository.findById(id)
                .map(autorInBD->{
                    autorInBD.setNombre(autorDTO.nombre());
                    autorInBD.setApellido(autorDTO.apellido());
                    autorInBD.setFechaNacimiento(autorDTO.fechaNacimiento());

                    return autorRepository.save(autorInBD);

        }).map(autorMapper::toDto);
    }

    @Override
    public List<AutorDTO> findAll() {
        return autorRepository.findAll().stream()
                .map(dto->autorMapper.toDto(dto))
                .collect(Collectors.toList());
    }
}
