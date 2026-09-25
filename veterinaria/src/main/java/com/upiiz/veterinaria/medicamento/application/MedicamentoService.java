package com.upiiz.veterinaria.medicamento.application;

import com.upiiz.veterinaria.medicamento.domain.models.Medicamento;
import com.upiiz.veterinaria.medicamento.domain.ports.in.MedicamentoUseCase;
import com.upiiz.veterinaria.medicamento.domain.ports.out.MedicamentoRepository;

import java.util.List;

public class MedicamentoService implements MedicamentoUseCase {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public Medicamento registrar(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public List<Medicamento> listar() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento buscarPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    @Override
    public Medicamento actualizar(Medicamento medicamento) {
        return medicamentoRepository.update(medicamento);
    }

    @Override
    public void eliminar(Long id) {
        medicamentoRepository.delete(id);

    }
}
