package com.pugs.dto.Equipo;

import lombok.Data;

import java.util.List;

@Data
public class EquipoRequestDTO {
    private String nombre;
    private List<Long> jugadoresIds;
}
