package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.LibroDTO;
import com.biblio.biblioteca.dto.LibroMapper;
import com.biblio.biblioteca.entity.Libro;
import com.biblio.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroServiceImp implements LibroService{

    private final LibroRepository libroRepository;
    private final LibroMapper libroMapper;


    public LibroServiceImp(LibroRepository libroRepository, LibroMapper libroMapper) {
        this.libroRepository = libroRepository;
        this.libroMapper = libroMapper;
    }


    @Override
    public Optional<LibroDTO> findById(Long id) {
        return libroRepository.findById(id)
                .map(libroMapper::toDTO);
    }

    @Override
    public Optional<LibroDTO> findByName(String titulo) {
        return libroRepository.findByTitulo(titulo)
                .map(libroMapper::toDTO);
    }

    @Override
    public LibroDTO save(LibroDTO libroDTO) {
        Libro libro = libroRepository.save(libroMapper.toEntity(libroDTO));
        return libroMapper.toDTO(libro);
    }

    @Override
    public void delete(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Optional<LibroDTO> update(Long id, LibroDTO libroDTO) {
        return libroRepository.findById(id)
                .map(libroInBD->{
                        libroInBD.setTitulo(libroDTO.titulo());
                        libroInBD.setAutor(libroDTO.autor());
                        libroInBD.setResumen(libroDTO.resumen());
                        libroInBD.setFechaPublicacion(libroDTO.fechaPublicacion());
                        libroInBD.setCantidadEjemplares(libroDTO.cantidadEjemplares());

                        return libroRepository.save(libroInBD);
                    }
                ).map(libroMapper::toDTO);
    }

    @Override
    public List<LibroDTO> findAll() {
        return libroRepository.findAll().stream()
                .map(dto-> libroMapper.toDTO(dto))
                .collect(Collectors.toList());
    }
}
