package com.upiiz.ejercicio_08.paciente;

import com.upiiz.ejercicio_08.paciente.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteEntity,Long> {
    Optional<PacienteEntity> findByTelefono(String telefono);
}
