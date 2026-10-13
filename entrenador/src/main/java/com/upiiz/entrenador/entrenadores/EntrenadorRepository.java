package com.upiiz.entrenador.entrenadores;

import com.upiiz.entrenador.entrenadores.entities.EntrenadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrenadorRepository extends JpaRepository<EntrenadorEntity,Long> {
    Optional<EntrenadorEntity> findByCorreoIgnoreCase(String correo);
}
