package com.pugs.service.ServiceImpl;

import com.pugs.dto.Equipo.EquipoRequestDTO;
import com.pugs.dto.Equipo.EquipoResponseDTO;
import com.pugs.entity.Equipo;
import com.pugs.mapper.Equipo.EquipoMapper;
import com.pugs.repository.Equipo.EquipoRepository;
import com.pugs.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;


    @Override
    public EquipoResponseDTO crear(EquipoRequestDTO dto) {
        if (equipoRepository.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("El equipo ya existe");
        }
        Equipo equipo = equipoMapper.toEntity(dto);
        return equipoMapper.toDto(equipoRepository.save(equipo));
    }

    @Override
    public EquipoResponseDTO actualizar(Long id, EquipoRequestDTO dto) {
        Equipo existente = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setJugadores(equipoMapper.toEntity(dto).getJugadores());

        return equipoMapper.toDto(equipoRepository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }

    @Override
    public EquipoResponseDTO obtenerPorId(Long id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        return equipoMapper.toDto(equipo);
    }

    @Override
    public List<EquipoResponseDTO> listar() {
        return equipoRepository.findAllConJugadores()
                .stream()
                .map(equipoMapper::toDto)
                .collect(Collectors.toList());
    }
}