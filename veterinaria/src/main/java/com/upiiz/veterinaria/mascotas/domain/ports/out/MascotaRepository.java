package com.upiiz.veterinaria.mascotas.domain.ports.out;

import com.upiiz.veterinaria.mascotas.domain.models.Mascota;

import java.util.List;

public interface MascotaRepository {
    Mascota save(Mascota mascota);
    Mascota findById(Long id);
    List<Mascota> findAll();
    Mascota update(Mascota mascota);
    void delete(Long id);
}
