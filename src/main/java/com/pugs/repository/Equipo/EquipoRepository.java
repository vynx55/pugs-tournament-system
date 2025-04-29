package com.pugs.repository.Equipo;

import com.pugs.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    boolean existsByNombre(String nombre);
    @Query("SELECT DISTINCT e FROM Equipo e LEFT JOIN FETCH e.jugadores")
    List<Equipo> findAllConJugadores();
}
