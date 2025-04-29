package com.pugs.mapper.Usuario;

import com.pugs.dto.Usuario.UsuarioRequestDTO;
import com.pugs.dto.Usuario.UsuarioResponseDTO;
import com.pugs.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioResponseDTO toDto(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .rol(usuario.getRol())
                .build();
    }

    public Usuario toEntity(UsuarioRequestDTO requestDTO) {
        return Usuario.builder()
                .username(requestDTO.getUsername())
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .nombre(requestDTO.getNombre())
                .apellido(requestDTO.getApellido())
                .rol(requestDTO.getRol())
                .build();
    }
}
