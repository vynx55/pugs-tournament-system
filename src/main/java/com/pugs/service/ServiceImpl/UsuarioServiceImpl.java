package com.pugs.service.ServiceImpl;

import com.pugs.dto.Usuario.UsuarioRequestDTO;
import com.pugs.dto.Usuario.UsuarioResponseDTO;
import com.pugs.entity.Usuario;
import com.pugs.entity.enumeraciones.Rol;
import com.pugs.mapper.Usuario.UsuarioMapper;
import com.pugs.repository.Usuario.UsuarioRepository;
import com.pugs.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    @Override
    public UsuarioResponseDTO guardar(UsuarioRequestDTO requestDTO) {
        Usuario usuario = usuarioMapper.toEntity(requestDTO);
        usuario.setRol(Rol.JUGADOR);
        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(guardado);
    }

    @Override
    public UsuarioResponseDTO obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .map(usuarioMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO requestDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (requestDTO.getRol() != null) {
            if (!isValidRole(requestDTO.getRol())) {
                throw new RuntimeException("Rol inválido");
            }
            usuarioExistente.setRol(requestDTO.getRol());
        }

        if (!Objects.equals(usuarioExistente.getEmail(), requestDTO.getEmail())) {
            if (usuarioRepository.existsByEmail(requestDTO.getEmail())) {
                throw new RuntimeException("El email ya está registrado por otro usuario");
            }
        }

        if (!Objects.equals(usuarioExistente.getUsername(), requestDTO.getUsername())) {
            if (usuarioRepository.existsByUsername(requestDTO.getUsername())) {
                throw new RuntimeException("El username ya está registrado por otro usuario");
            }
        }

        usuarioExistente.setNombre(requestDTO.getNombre());
        usuarioExistente.setApellido(requestDTO.getApellido());
        usuarioExistente.setUsername(requestDTO.getUsername());
        usuarioExistente.setEmail(requestDTO.getEmail());

        Usuario actualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toDto(actualizado);
    }

    private boolean isValidRole(Rol rol) {
        for (Rol r : Rol.values()) {
            if (r == rol) {
                return true;
            }
        }
        return false;
    }
}

