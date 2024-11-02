package com.biblio.biblioteca.service;

import com.biblio.biblioteca.dto.UsuarioDTO;
import com.biblio.biblioteca.dto.UsuarioMapper;
import com.biblio.biblioteca.entity.Usuario;
import com.biblio.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImp implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTOWithoutId);
    }

    @Override
    public Optional<UsuarioDTO> findByEmail(String email) {
        return usuarioRepository.findByEmail(email).map(usuarioMapper::toDTOWithoutId);
    }

    @Override
    public Optional<UsuarioDTO> findByNumeroDocumento(Integer numeroDocumento) {
        return usuarioRepository.findByNumeroDocumento(numeroDocumento).map(usuarioMapper::toDTOWithoutId);
    }

    @Override
    public Optional<UsuarioDTO> findByName(String name) {
        return usuarioRepository.findByName(name)
                .map(usuarioMapper::toDTOWithoutId);
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<UsuarioDTO> update(Long id, UsuarioDTO newusuarioDTO) {
        return usuarioRepository.findById(id)
                .map(usuarioInBD -> {
                        usuarioInBD.setNombre(newusuarioDTO.nombre());
                        usuarioInBD.setApellido(newusuarioDTO.apellido());
                        usuarioInBD.setEmail(newusuarioDTO.email());
                        usuarioInBD.setTelefono(newusuarioDTO.telefono());
                        usuarioInBD.setFechaNacimiento(newusuarioDTO.fechNacimiento());
                        usuarioInBD.setClave(newusuarioDTO.clave());
                        usuarioInBD.setDireccion(newusuarioDTO.direccion());
                        usuarioInBD.setTipoDocumento(newusuarioDTO.tipoDocumento());
                        usuarioInBD.setNumeroDocumento(newusuarioDTO.numeroDocumento());
                        usuarioInBD.setRol(newusuarioDTO.rol());

                        return usuarioRepository.save(usuarioInBD);

                    }
                ).map(usuarioMapper::toDTO);
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(dto->usuarioMapper.toDTO(dto))
                .collect(Collectors.toList());
    }
}
