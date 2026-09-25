package com.upiiz.veterinaria.medicamento.domain.ports.in;

import com.upiiz.veterinaria.medicamento.domain.models.Medicamento;

import java.util.List;

public interface MedicamentoUseCase {
    Medicamento registrar(Medicamento medicamento);
    List<Medicamento> listar();
    Medicamento buscarPorId(Long id);
    Medicamento actualizar(Medicamento medicamento);
    void eliminar(Long id);
}
