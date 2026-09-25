package com.upiiz.veterinaria.mascotas.application;

import com.upiiz.veterinaria.mascotas.domain.models.Mascota;
import com.upiiz.veterinaria.mascotas.domain.ports.in.MascotaUseCase;
import com.upiiz.veterinaria.mascotas.domain.ports.out.MascotaRepository;

import java.util.List;

public class MascotaService implements MascotaUseCase {

    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public Mascota registrar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public List<Mascota> listar() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota buscarPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public Mascota actualizar(Mascota mascota) {
        return mascotaRepository.update(mascota);
    }

    @Override
    public void eliminar(Long id) {
        mascotaRepository.delete(id);

    }
}
