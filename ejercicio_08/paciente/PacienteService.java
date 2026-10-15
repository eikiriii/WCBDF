package com.upiiz.ejercicio_08.paciente;

import com.upiiz.ejercicio_08.paciente.dto.NuevoPacienteDto;
import com.upiiz.ejercicio_08.paciente.dto.PacienteDto;

import java.util.List;

public interface PacienteService {

    PacienteDto save(NuevoPacienteDto nuevopacienteDto);
    List<PacienteDto> FindAll();
    PacienteDto FindById(Long id);
    PacienteDto update(Long id, NuevoPacienteDto updatePacienteDto);
    void delete(Long id);
}
