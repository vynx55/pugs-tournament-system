package com.pugs.dto.Usuario;

import com.pugs.entity.enumeraciones.Rol;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String username;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private Rol rol;
}
