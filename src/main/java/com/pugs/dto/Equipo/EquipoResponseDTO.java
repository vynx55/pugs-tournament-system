package com.pugs.dto.Equipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipoResponseDTO {
    private Long id;
    private String nombre;
    private List<String> jugadoresUsernames;
}
