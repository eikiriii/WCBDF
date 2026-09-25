package com.upiiz.veterinaria.mascotas.infraestructure.out;

import com.upiiz.veterinaria.mascotas.domain.models.Mascota;
import com.upiiz.veterinaria.mascotas.domain.ports.out.MascotaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MascotaRepositoryimpl implements MascotaRepository {

    private final MascotaRepositoryJpa mascotaRepositoryJpa;

    public MascotaRepositoryimpl(MascotaRepositoryJpa mascotaRepositoryJpa) {
        this.mascotaRepositoryJpa = mascotaRepositoryJpa;
    }

    @Override
    public Mascota save(Mascota mascota) {
        MascotaEntity mascotaEntity = new MascotaEntity(null, mascota.getNombreMascota(), mascota.getNombreDueno(), mascota.getEspecie(), mascota.getRaza(), mascota.getEdad());
        MascotaEntity mascotaGuardada = mascotaRepositoryJpa.save(mascotaEntity);
        return toDomain(mascotaGuardada);
    }

    @Override
    public Mascota findById(Long id) {
        return mascotaRepositoryJpa.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public List<Mascota> findAll() {
        return mascotaRepositoryJpa.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Mascota update(Mascota mascota) {
        // Primero vemos si la mascota existe; si no, no hay nada que actualizar
        return mascotaRepositoryJpa.findById(mascota.getId())
                .map(entityExistente -> {
                    entityExistente.setNombreMascota(mascota.getNombreMascota());
                    entityExistente.setNombreDueno(mascota.getNombreDueno());
                    entityExistente.setEspecie(mascota.getEspecie());
                    entityExistente.setRaza(mascota.getRaza());
                    entityExistente.setEdad(mascota.getEdad());
                    MascotaEntity actualizada = mascotaRepositoryJpa.save(entityExistente);
                    return toDomain(actualizada);
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        mascotaRepositoryJpa.deleteById(id);
    }

    private Mascota toDomain(MascotaEntity entity) { // para no repetir lo mismo en cada funcion
        return new Mascota(entity.getId(), entity.getNombreMascota(), entity.getNombreDueno(), entity.getEspecie(), entity.getRaza(), entity.getEdad());
    }
}
