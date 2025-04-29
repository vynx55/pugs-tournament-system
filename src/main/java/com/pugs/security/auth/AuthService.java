package com.pugs.security.auth;

import com.pugs.entity.Usuario;
import com.pugs.entity.enumeraciones.Rol;
import com.pugs.repository.Usuario.UsuarioRepository;
import com.pugs.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Método de registro de usuario
    public String register(String username, String password, String email, String nombre, String apellido) {
        String encryptedPassword = passwordEncoder.encode(password);

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(encryptedPassword);
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);

        usuario.setRol(Rol.JUGADOR);
        usuarioRepository.save(usuario);
        return jwtService.generateToken(usuario.getUsername());
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        return jwtService.generateToken(username);
    }
}
