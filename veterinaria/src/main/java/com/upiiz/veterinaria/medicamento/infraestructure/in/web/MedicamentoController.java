package com.upiiz.veterinaria.medicamento.infraestructure.in.web;

import com.upiiz.veterinaria.mascotas.domain.models.Mascota;
import com.upiiz.veterinaria.medicamento.domain.models.Medicamento;
import com.upiiz.veterinaria.medicamento.domain.ports.in.MedicamentoUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicamentos")

public class MedicamentoController {
    private final MedicamentoUseCase medicamentoUseCase;

    public MedicamentoController(MedicamentoUseCase medicamentoUseCase) {
        this.medicamentoUseCase = medicamentoUseCase;
    }

    @GetMapping
    public List<Medicamento> getMedicamento(){
        return medicamentoUseCase.listar();
    }

    @GetMapping("/{id}")
    public Medicamento getMedicamento(@PathVariable Long id){
        return medicamentoUseCase.buscarPorId(id);
    }

    @PostMapping
    public Medicamento createMedicamento(@RequestBody final Medicamento medicamento){
        return medicamentoUseCase.registrar(medicamento);
    }

    @PutMapping("/{id}")
    public Medicamento updateMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamento){
        medicamento.setId(id);
        return medicamentoUseCase.actualizar(medicamento);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicamento(@PathVariable final Long id){
        medicamentoUseCase.eliminar(id);
    }
}
