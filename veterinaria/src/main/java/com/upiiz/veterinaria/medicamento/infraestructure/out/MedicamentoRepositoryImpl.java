package com.upiiz.veterinaria.medicamento.infraestructure.out;

import com.upiiz.veterinaria.medicamento.domain.models.Medicamento;
import com.upiiz.veterinaria.medicamento.domain.ports.out.MedicamentoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MedicamentoRepositoryImpl implements MedicamentoRepository {
    private final MedicamentoRepositoryJpa medicamentoRepositoryJpa;

    public MedicamentoRepositoryImpl(MedicamentoRepositoryJpa medicamentoRepositoryJpa) {
        this.medicamentoRepositoryJpa = medicamentoRepositoryJpa;
    }

    @Override
    public Medicamento save(Medicamento medicamento) {
        MedicamentoEntity medicamentoEntity = new MedicamentoEntity(null,medicamento.getNombre(), medicamento.getPrecio());
        MedicamentoEntity medicamentoGuardado = medicamentoRepositoryJpa.save(medicamentoEntity);
        return toDomain(medicamentoGuardado);
    }

    @Override
    public Medicamento findById(Long id) {

        return medicamentoRepositoryJpa.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public List<Medicamento> findAll() {

        return medicamentoRepositoryJpa.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Medicamento update(Medicamento medicamento) {
        return medicamentoRepositoryJpa.findById(medicamento.getId())
                .map(medicamentoExistente -> {
                    medicamentoExistente.setNombre(medicamento.getNombre());
                    medicamentoExistente.setPrecio(medicamento.getPrecio());
                    MedicamentoEntity actualizado = medicamentoRepositoryJpa.save(medicamentoExistente);
                    return toDomain(actualizado);
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        medicamentoRepositoryJpa.deleteById(id);

    }

    private Medicamento toDomain(MedicamentoEntity medicamento){
        return new Medicamento(medicamento.getId(),medicamento.getNombre(),medicamento.getPrecio());
    }
}
