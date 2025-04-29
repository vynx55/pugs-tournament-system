package com.pugs.controller;

import com.pugs.dto.Usuario.UsuarioRequestDTO;
import com.pugs.dto.Usuario.UsuarioResponseDTO;
import com.pugs.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.listar();
    }

    @PostMapping
    public UsuarioResponseDTO guardar(@RequestBody UsuarioRequestDTO requestDTO) {
        return usuarioService.guardar(requestDTO);
    }

    @GetMapping("/{username}")
    public UsuarioResponseDTO obtenerPorUsername(@PathVariable String username) {
        return usuarioService.obtenerPorUsername(username);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO requestDTO) {
        return usuarioService.actualizar(id, requestDTO);
    }
}
