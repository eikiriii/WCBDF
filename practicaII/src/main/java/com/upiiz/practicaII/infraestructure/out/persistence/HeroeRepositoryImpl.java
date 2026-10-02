package com.upiiz.practicaII.infraestructure.out.persistence;

import com.upiiz.practicaII.domain.models.Heroe;
import com.upiiz.practicaII.domain.ports.out.HeroeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeroeRepositoryImpl implements HeroeRepository {
    // Aquí ya hacemos que todo funcione (creo)

    private final HeroeRepositoryJpa heroeRepositoryJpa;

    public HeroeRepositoryImpl(HeroeRepositoryJpa heroeRepositoryJpa) {
        this.heroeRepositoryJpa = heroeRepositoryJpa;
    }

    @Override
    public Heroe save(Heroe heroe) {
        HeroeEntity heroeEntity = toEntity(heroe);
        HeroeEntity heroeGuardado = heroeRepositoryJpa.save(heroeEntity);
        return toDomain(heroeGuardado);
    }

    @Override
    public List<Heroe> findAll() {
        return heroeRepositoryJpa.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Heroe> findByEpoca(String epoca) {
        return heroeRepositoryJpa.findByEpoca(epoca)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Heroe> findByMovimiento(String movimiento) {
        return heroeRepositoryJpa.findByMovimiento(movimiento)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Heroe> findByEstado(String estado) {
        return heroeRepositoryJpa.findByEstadoNacimiento(estado)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Heroe findById(Long id) {
        return heroeRepositoryJpa.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public Heroe update(Heroe heroe) {
        HeroeEntity heroeEntity = toEntity(heroe);
        HeroeEntity heroeActualizado = heroeRepositoryJpa.save(heroeEntity);
        return toDomain(heroeActualizado);
    }

    @Override
    public void delete(Long id) {
        heroeRepositoryJpa.deleteById(id);
    }

    // ---------- Mapeos ----------

    private HeroeEntity toEntity(Heroe heroe) {
        return new HeroeEntity(
                heroe.getId(),
                heroe.getNombre(),
                heroe.getApellido(),
                heroe.getFechaNacimiento(),
                heroe.getEstadoNacimiento(),
                heroe.getEpoca(),
                heroe.getMovimiento(),
                heroe.getDescripcion()
        );
    }

    private Heroe toDomain(HeroeEntity entity) {
        return new Heroe(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getFechaNacimiento(),
                entity.getEstadoNacimiento(),
                entity.getEpoca(),
                entity.getMovimiento(),
                entity.getDescripcion()
        );
    }

}
