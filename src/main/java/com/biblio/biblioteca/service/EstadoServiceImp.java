package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.EstadoDTO;
import com.biblio.biblioteca.dto.EstadoMapper;
import com.biblio.biblioteca.entity.Estado;
import com.biblio.biblioteca.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstadoServiceImp implements EstadoService{

    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper;

    public EstadoServiceImp(EstadoRepository estadoRepository, EstadoMapper estadoMapper) {
        this.estadoRepository = estadoRepository;
        this.estadoMapper = estadoMapper;
    }


    @Override
    public Optional<EstadoDTO> findById(Long id) {
        return estadoRepository.findById(id).map(estadoMapper::toDTO);
    }

    @Override
    public Optional<EstadoDTO> findByName(Long id) {
        return estadoRepository.findById(id).map(estadoMapper::toDTO);
    }

    @Override
    public EstadoDTO save(EstadoDTO estadoDTO) {
        Estado estado = estadoRepository.save(estadoMapper.toEntity(estadoDTO));
        return estadoMapper.toDTO(estado);
    }

    @Override
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public Optional<EstadoDTO> update(Long id, EstadoDTO estadoDTO) {
        return estadoRepository.findById(id).map(estadoInBD->{
                    estadoInBD.setNombre(estadoDTO.nombre());
                    estadoInBD.setDescripcion(estadoDTO.descripcion());

                    return estadoRepository.save(estadoInBD);
                }
        ).map(estadoMapper::toDTO);
    }

    @Override
    public List<EstadoDTO> findAll() {
        return estadoRepository.findAll().stream()
                .map(dto->estadoMapper.toDTO(dto))
                .collect(Collectors.toList());
    }
}
