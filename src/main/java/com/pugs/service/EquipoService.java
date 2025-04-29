package com.pugs.service;

import com.pugs.dto.Equipo.EquipoRequestDTO;
import com.pugs.dto.Equipo.EquipoResponseDTO;

import java.util.List;

public interface EquipoService {
    EquipoResponseDTO crear(EquipoRequestDTO dto);
    EquipoResponseDTO actualizar(Long id, EquipoRequestDTO dto);
    void eliminar(Long id);
    EquipoResponseDTO obtenerPorId(Long id);
    List<EquipoResponseDTO> listar();
}
