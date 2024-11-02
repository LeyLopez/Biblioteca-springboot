package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.ReservaDTO;
import com.biblio.biblioteca.dto.ReservaMapper;
import com.biblio.biblioteca.entity.Reserva;
import com.biblio.biblioteca.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImp implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    public ReservaServiceImp(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }

    @Override
    public Optional<ReservaDTO> findById(Long id) {
        return reservaRepository.findById(id).map(reservaMapper::toDTO);
    }


    @Override
    public ReservaDTO save(ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.save(reservaMapper.toEntity(reservaDTO));
        return reservaMapper.toDTO(reserva);
    }

    @Override
    public void delete(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public Optional<ReservaDTO> update(Long id, ReservaDTO reservaDTO) {
        return reservaRepository.findById(id).map(reservaInBD->{
            reservaInBD.setFechaReserva(reservaDTO.fechaReserva());
            reservaInBD.setFechaVencimientoReserva(reservaDTO.fechaVencimientoReserva());
            reservaInBD.setEstado(reservaDTO.estado());
            reservaInBD.setFechaCambioEstado(reservaDTO.fechaCambioEstado());

            return reservaRepository.save(reservaInBD);
        }).map(reservaMapper::toDTO);
    }

    @Override
    public List<ReservaDTO> findAll() {
        return reservaRepository.findAll().stream()
                .map(dto->reservaMapper.toDTO(dto))
                .collect(Collectors.toList());
    }
}
