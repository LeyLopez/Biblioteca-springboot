package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.GeneroDTO;
import com.biblio.biblioteca.dto.GeneroMapper;
import com.biblio.biblioteca.entity.Genero;
import com.biblio.biblioteca.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroServiceImp implements GeneroService {

    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;

    public GeneroServiceImp(GeneroRepository generoRepository, GeneroMapper generoMapper) {
        this.generoRepository = generoRepository;
        this.generoMapper = generoMapper;
    }


    @Override
    public Optional<GeneroDTO> findById(Long ig) {
        return generoRepository.findById(ig).map(generoMapper::toDTO);
    }

    @Override
    public Optional<GeneroDTO> findByName(String name) {
        return generoRepository.findByName(name).map(generoMapper::toDTO);
    }

    @Override
    public GeneroDTO save(GeneroDTO generoDTO) {
        Genero genero = generoMapper.toEntity(generoDTO);
        return generoMapper.toDTO(genero);
    }

    @Override
    public void delete(Long id) {
        generoRepository.deleteById(id);
    }

    @Override
    public Optional<GeneroDTO> update(Long id, GeneroDTO generoDTO) {
        return generoRepository.findById(id).map(generoInBD-> {
                    generoInBD.setNombre(generoDTO.nombre());

                    return generoRepository.save(generoInBD);
                }
        ).map(generoMapper::toDTO);
    }

    @Override
    public List<GeneroDTO> findAll() {
        return generoRepository.findAll().stream()
                .map(dto->generoMapper.toDTO(dto))
                .collect(Collectors.toList());
    }
}
