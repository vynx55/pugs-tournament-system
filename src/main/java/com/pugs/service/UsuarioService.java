package com.pugs.service;

import com.pugs.dto.Usuario.UsuarioRequestDTO;
import com.pugs.dto.Usuario.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDTO> listar();
    UsuarioResponseDTO guardar(UsuarioRequestDTO requestDTO);
    UsuarioResponseDTO  obtenerPorUsername(String username);
    UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO requestDTO);
}
