package com.biblio.biblioteca.api;



import com.biblio.biblioteca.dto.LoanDTO;
import com.biblio.biblioteca.dto.ReservationDTO;
import com.biblio.biblioteca.dto.UserDTO;
import com.biblio.biblioteca.exception.NotFoundException;
import com.biblio.biblioteca.security.service.LoanService;
import com.biblio.biblioteca.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserAPI {

    private final UserService userService;


    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Obtener todos las usuarios.", description = "Retorna una lista con todos los usuarios de la biblioteca.")
    @GetMapping
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Obtener un usuario por ID.", description = "Retorna el objeto usuario con el ID proporcionado.")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return userService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElseThrow(()->new NotFoundException("No se encontró el usuario con el ID "+id));
    }

    @Operation(summary = "Crear un usuario sino existe, si existe, el usuario se actualizará con los nuevos datos.",
            description = "Retorna el objeto usuario creado.")
    @PostMapping
    public ResponseEntity<UserDTO> createdUser(@RequestBody UserDTO usuario) {
        return createUser(usuario);
    }

    private ResponseEntity<UserDTO> createUser(UserDTO usuario) {
        UserDTO newUsuario = userService.save(usuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newUsuario.id()).toUri();
        return ResponseEntity.created(location).body(newUsuario);
    }

    @Operation(summary = "Actualizar un usuario por ID.",  description = "Retorna el objeto usuario con los datos actualizados.")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO usuario) {
        Optional<UserDTO> usuarioToUpdate = userService.update(id, usuario);
        return usuarioToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->createUser(usuario));
    }

    @Operation(summary = "Eliminar usuario por ID.", description = "Retorna el objeto usuario eliminado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(l->{
                    userService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElseThrow(()->new NotFoundException("No se encontró el usuario con el ID "+id));
    }

    @Operation(summary = "Obtener un usuario por su username.", description = "Retorna el objeto usuario con el username proporcionado.")
    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(u->ResponseEntity.ok().body(u))
                .orElseThrow(()->new NotFoundException("No se pudo encontrar el cliente con el username "+username));
    }

    @Operation(summary = "Obtener las reservaciones que ha realizado un usuario con el ID proporcionado.",
        description = "Retorna una lista con objetos reserva realizados por un usuario.")
    @GetMapping("/reservas/{id}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findReservationsByUserId(id));
    }

    @Operation(summary = "Obtener los préstamos que ha realizado un usuario con el ID proporcionado.",
            description = "Retorna una lista con objetos préstamos realizados por un usuario.")
    @GetMapping("/prestamos/{id}")
    public ResponseEntity<List<LoanDTO>> getLoansByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findLoanByUserId(id));
    }


}
