package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.PrestamoDTO;
import com.biblio.biblioteca.dto.PrestamoMapper;
import com.biblio.biblioteca.entity.Prestamo;
import com.biblio.biblioteca.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestamoServiceImp implements PrestamoService{

    private final PrestamoRepository prestamoRepository;
    private final PrestamoMapper prestamoMapper;

    public PrestamoServiceImp(PrestamoRepository prestamoRepository, PrestamoMapper prestamoMapper) {
        this.prestamoRepository = prestamoRepository;
        this.prestamoMapper = prestamoMapper;
    }


    @Override
    public Optional<PrestamoDTO> findById(Long id) {
        return prestamoRepository.findById(id).map(prestamoMapper::toDTO);
    }


    @Override
    public PrestamoDTO save(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = prestamoRepository.save(prestamoMapper.toEntity(prestamoDTO));
        return prestamoMapper.toDTO(prestamo);
    }

    @Override
    public void delete(Long id) {
        prestamoRepository.deleteById(id);
    }

    @Override
    public Optional<PrestamoDTO> update(Long id, PrestamoDTO prestamoDTO) {
        return prestamoRepository.findById(id).map(prestamoInBD->{
            prestamoInBD.setFechaPrestamo(prestamoDTO.fechaPrestamo());
            prestamoInBD.setFechaDevolucion(prestamoDTO.fechaDevolucion());
            prestamoInBD.setEstado(prestamoDTO.estado());
            prestamoInBD.setFechaCambioEstado(prestamoDTO.fechaCambioEstado());

            return prestamoRepository.save(prestamoInBD);

        }).map(prestamoMapper::toDTO);
    }

    @Override
    public List<PrestamoDTO> findAll() {
        return prestamoRepository.findAll().stream()
                .map(dto->prestamoMapper.toDTO(dto))
                .collect(Collectors.toList());
    }
}
