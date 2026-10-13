package com.upiiz.entrenador.entrenadores;

import com.upiiz.entrenador.entrenadores.dto.EntrenadorDto;
import com.upiiz.entrenador.entrenadores.dto.NuevoEntrenadorDto;
import com.upiiz.entrenador.entrenadores.entities.EntrenadorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class EntrenadorServiceImpl implements EntrenadorService{
    EntrenadorRepository entrenadorRepository;

    public EntrenadorServiceImpl(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    @Override
    public EntrenadorDto save(NuevoEntrenadorDto nuevoEntrenadorDto) {
        entrenadorRepository.findByCorreoIgnoreCase(nuevoEntrenadorDto.getCorreo()).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe una entrenador con ese correo: " + nuevoEntrenadorDto.getCorreo());
        });
        EntrenadorEntity entrenadorNuevo= new EntrenadorEntity(nuevoEntrenadorDto.getNombre(), nuevoEntrenadorDto.getEspecialidad(),nuevoEntrenadorDto.getExperiencia(),nuevoEntrenadorDto.getCorreo());
        EntrenadorEntity entrenadorGuardado = entrenadorRepository.save(entrenadorNuevo);
        return new EntrenadorDto(entrenadorGuardado.getId(), entrenadorGuardado.getNombre(), entrenadorGuardado.getEspecialidad(), entrenadorGuardado.getExperiencia(), entrenadorGuardado.getCorreo());
    }

    @Override
    public List<EntrenadorDto> findAll() {
        return entrenadorRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public EntrenadorDto findById(Long id) {
        return toDto(buscarOFallar(id));
    }

    @Override
    public EntrenadorDto update(Long id, NuevoEntrenadorDto updateEntrenadorDto) {
        EntrenadorEntity entrenador = buscarOFallar(id);

        // El correo no debe pertenecer a OTRO entrenador
        entrenadorRepository.findByCorreoIgnoreCase(updateEntrenadorDto.getCorreo())
                .filter(e -> !e.getId().equals(id))
                .ifPresent(e -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            "Ya existe un entrenador con ese correo: " + updateEntrenadorDto.getCorreo());
                });

        entrenador.setNombre(updateEntrenadorDto.getNombre());
        entrenador.setEspecialidad(updateEntrenadorDto.getEspecialidad());
        entrenador.setExperiencia(updateEntrenadorDto.getExperiencia());
        entrenador.setCorreo(updateEntrenadorDto.getCorreo());

        return toDto(entrenadorRepository.save(entrenador));
    }

    @Override
    public void delete(Long id) {
        if (!entrenadorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No existe un entrenador con id: " + id);
        }
        entrenadorRepository.deleteById(id);
    }

    private EntrenadorEntity buscarOFallar(Long id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un entrenador con id: " + id));
    }

    private EntrenadorDto toDto(EntrenadorEntity e) {
        return new EntrenadorDto(e.getId(), e.getNombre(), e.getEspecialidad(),
                e.getExperiencia(), e.getCorreo());
    }
}
