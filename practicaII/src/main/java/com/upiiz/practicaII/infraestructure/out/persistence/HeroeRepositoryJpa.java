package com.upiiz.practicaII.infraestructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroeRepositoryJpa extends JpaRepository<HeroeEntity,Long> {
    List<HeroeEntity> findByEpoca(String epoca);

    List<HeroeEntity> findByMovimiento(String movimiento);

    List<HeroeEntity> findByEstadoNacimiento(String estadoNacimiento);
}
