package com.upiiz.veterinaria.mascotas.infraestructure.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepositoryJpa extends JpaRepository<MascotaEntity,Long> {
}
