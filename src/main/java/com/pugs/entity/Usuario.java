package com.pugs.entity;

import com.pugs.entity.enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String password;
    private String nombre;
    private String apellido;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private List<EquipoUsuario> equipos;

    @OneToMany(mappedBy = "creador")
    private List<Torneo> torneosCreados;

    @ManyToMany(mappedBy = "jugadores")
    private List<Match> matchs;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
