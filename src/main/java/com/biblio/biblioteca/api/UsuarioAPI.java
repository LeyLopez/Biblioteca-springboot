package com.biblio.biblioteca.api;



import com.biblio.biblioteca.dto.UsuarioDTO;
import com.biblio.biblioteca.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioAPI {

    private final UsuarioService usuarioService;


    public UsuarioAPI(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioId(@PathVariable("id") Long id) {
        return usuarioService.findById(id).map(l->ResponseEntity.ok().body(l))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuario) {
        return createUsuario(usuario);
    }

    private ResponseEntity<UsuarioDTO> createUsuario(UsuarioDTO usuario) {
        UsuarioDTO newUsuario = usuarioService.save(usuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newUsuario.id()).toUri();
        return ResponseEntity.created(location).body(newUsuario);
    }


    @PutMapping("/id")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuario) {
        Optional<UsuarioDTO> usuarioToUpdate = usuarioService.update(id, usuario);
        return usuarioToUpdate.map(l->ResponseEntity.ok(l))
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @DeleteMapping
    public ResponseEntity<UsuarioDTO> eliminarUsuario(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(l->{
                    usuarioService.delete(id);
                    return ResponseEntity.ok().body(l);
                }).orElse(ResponseEntity.notFound().build());
    }
}
