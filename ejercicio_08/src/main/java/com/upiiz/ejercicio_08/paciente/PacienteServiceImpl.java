package com.upiiz.ejercicio_08.paciente;

import com.upiiz.ejercicio_08.paciente.dto.NuevoPacienteDto;
import com.upiiz.ejercicio_08.paciente.dto.PacienteDto;
import com.upiiz.ejercicio_08.paciente.entities.PacienteEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService{

    private PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteDto save(NuevoPacienteDto p) {
        pacienteRepository.findByTelefono(p.getTelefono()).ifPresent(e -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            "Ya existe un paciente con ese numero de teléfono: " + p.getTelefono());
                });
        PacienteEntity nuevoPaciente = new PacienteEntity(p.getNombre(), p.getEdad(),p.getGenero(), p.getTelefono(), p.isSeguro());
        PacienteEntity pacienteGuardado = pacienteRepository.save(nuevoPaciente);
        return new PacienteDto(pacienteGuardado.getId(), pacienteGuardado.getNombre(), pacienteGuardado.getEdad(), pacienteGuardado.getGenero(), pacienteGuardado.getTelefono(), pacienteGuardado.isSeguro());
    }

    @Override
    public List<PacienteDto> FindAll() {
        return pacienteRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public PacienteDto FindById(Long id) {
        return toDto(buscar(id));
    }

    @Override
    public PacienteDto update(Long id, NuevoPacienteDto up) {
        PacienteEntity paciente = buscar(id);
        pacienteRepository.findByTelefono(up.getTelefono()).filter(e -> !e.getId().equals(id)).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un paciente con ese numero de teléfono: " + up.getTelefono());
        });
        paciente.setNombre(up.getNombre());
        paciente.setEdad(up.getEdad());
        paciente.setGenero(up.getGenero());
        paciente.setTelefono(up.getTelefono());
        paciente.setSeguro(up.isSeguro());
        return toDto(pacienteRepository.save(paciente));
    }

    @Override
    public void delete(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No existe un paciente con id: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    private PacienteEntity buscar(Long id){
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un paciente con id: " + id));
    }

    private PacienteDto toDto(PacienteEntity paciente){
        return new PacienteDto(paciente.getId(), paciente.getNombre(), paciente.getEdad(), paciente.getGenero(), paciente.getTelefono(), paciente.isSeguro());
    }
}
