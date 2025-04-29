package com.pugs.dto.Usuario;

import com.pugs.entity.enumeraciones.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private Rol rol;

}
