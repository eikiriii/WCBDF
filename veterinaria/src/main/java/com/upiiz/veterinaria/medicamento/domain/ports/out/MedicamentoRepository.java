package com.upiiz.veterinaria.medicamento.domain.ports.out;

import com.upiiz.veterinaria.mascotas.domain.models.Mascota;
import com.upiiz.veterinaria.medicamento.domain.models.Medicamento;

import java.util.List;

public interface MedicamentoRepository {
    Medicamento save(Medicamento medicamento);
    Medicamento findById(Long id);
    List<Medicamento> findAll();
    Medicamento update(Medicamento medicamento);
    void delete(Long id);
}
