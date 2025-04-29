package com.pugs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipo_id")
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "equipo", fetch = FetchType.EAGER)
    private List<Usuario> jugadores;

    @ManyToMany
    @JoinTable(
            name = "equipo_torneo",
            joinColumns = @JoinColumn(name = "equipo_id"),
            inverseJoinColumns = @JoinColumn(name = "torneo_id")
    )
    private List<Torneo> torneos;

    @OneToMany(mappedBy = "equipo1")
    private List<Match> matchs1;

    @OneToMany(mappedBy = "equipo2")
    private List<Match> matchs2;
}
