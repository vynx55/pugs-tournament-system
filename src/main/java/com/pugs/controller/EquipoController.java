package com.pugs.controller;


import com.pugs.dto.Equipo.EquipoRequestDTO;
import com.pugs.dto.Equipo.EquipoResponseDTO;
import com.pugs.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    @PostMapping
    public EquipoResponseDTO crear(@RequestBody EquipoRequestDTO dto) {
        return equipoService.crear(dto);
    }

    @PutMapping("/{id}")
    public EquipoResponseDTO actualizar(@PathVariable Long id, @RequestBody EquipoRequestDTO dto) {
        return equipoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        equipoService.eliminar(id);
    }

    @GetMapping("/{id}")
    public EquipoResponseDTO obtener(@PathVariable Long id) {
        return equipoService.obtenerPorId(id);
    }

    @GetMapping
    public List<EquipoResponseDTO> listar() {
        return equipoService.listar();
    }
}
