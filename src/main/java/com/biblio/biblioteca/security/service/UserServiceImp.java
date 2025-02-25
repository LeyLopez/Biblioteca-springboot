package com.biblio.biblioteca.security.service;

import com.biblio.biblioteca.dto.UserDTO;
import com.biblio.biblioteca.dto.UserMapper;
import com.biblio.biblioteca.entity.User;
import com.biblio.biblioteca.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTOWithoutId);
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDTOWithoutId);
    }

    @Override
    public Optional<UserDTO> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).map(userMapper::toDTOWithoutId);
    }

    @Override
    public Optional<UserDTO> findByName(String name) {
        return userRepository.findByName(name)
                .map(userMapper::toDTOWithoutId);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDTO(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDTO> update(Long id, UserDTO newusuarioDTO) {
        return userRepository.findById(id)
                .map(usuarioInBD -> {
                        usuarioInBD.setName(newusuarioDTO.name());
                        usuarioInBD.setLastname(newusuarioDTO.lastname());
                        usuarioInBD.setEmail(newusuarioDTO.email());
                        usuarioInBD.setPhoneNumber(newusuarioDTO.phoneNumber());
                        usuarioInBD.setDateOfBirth(newusuarioDTO.dateOfBirth());
                        usuarioInBD.setPassword(newusuarioDTO.password());
                        usuarioInBD.setAddress(newusuarioDTO.address());
                        usuarioInBD.setKindOfDocument(newusuarioDTO.kindOfDocument());
                        usuarioInBD.setDocumentNumber(newusuarioDTO.documentNumber());

                        return userRepository.save(usuarioInBD);

                    }
                ).map(userMapper::toDTO);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(dto-> userMapper.toDTO(dto))
                .collect(Collectors.toList());
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
