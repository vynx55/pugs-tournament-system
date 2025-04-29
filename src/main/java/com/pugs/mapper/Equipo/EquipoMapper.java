package com.pugs.mapper.Equipo;

import com.pugs.dto.Equipo.EquipoRequestDTO;
import com.pugs.dto.Equipo.EquipoResponseDTO;
import com.pugs.entity.Equipo;
import com.pugs.entity.Usuario;
import com.pugs.repository.Usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EquipoMapper {

    private final UsuarioRepository usuarioRepository;

    public Equipo toEntity(EquipoRequestDTO dto) {
        Equipo equipo = new Equipo();
        equipo.setNombre(dto.getNombre());

        if (dto.getJugadoresIds() != null) {
            List<Usuario> jugadores = usuarioRepository.findAllById(dto.getJugadoresIds());
            equipo.setJugadores(jugadores);
        }

        return equipo;
    }

    public EquipoResponseDTO toDto(Equipo equipo) {
        List<String> usernames = equipo.getJugadores() != null
                ? equipo.getJugadores().stream().map(Usuario::getUsername).toList()
                : List.of();

        return EquipoResponseDTO.builder()
                .id(equipo.getId())
                .nombre(equipo.getNombre())
                .jugadoresUsernames(usernames)
                .build();
    }
}