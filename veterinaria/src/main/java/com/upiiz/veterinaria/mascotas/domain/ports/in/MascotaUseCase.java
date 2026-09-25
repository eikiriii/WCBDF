package com.upiiz.veterinaria.mascotas.domain.ports.in;

import com.upiiz.veterinaria.mascotas.domain.models.Mascota;

import java.util.List;

public interface MascotaUseCase {
    Mascota registrar(Mascota mascota);
    List<Mascota> listar();
    Mascota buscarPorId(Long id);
    Mascota actualizar(Mascota mascota);
    void eliminar(Long id);
}
