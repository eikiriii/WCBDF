package com.upiiz.actividad_04.alumno;
import com.upiiz.actividad_04.alumno.dto.AlumnoDto;
import com.upiiz.actividad_04.alumno.dto.NuevoAlumnoDto;
import com.upiiz.actividad_04.alumno.dto.ReporteEdadDto;
import com.upiiz.actividad_04.alumno.entities.AlumnoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public AlumnoDto save(NuevoAlumnoDto nuevoAlumnoDto){
        alumnoRepository.findByBoleta(nuevoAlumnoDto.getBoleta()).ifPresent(e -> {
            throw new IllegalArgumentException("La boleta " + nuevoAlumnoDto.getBoleta() + " ya está registrada.");
        });

        alumnoRepository.findByCorreoIgnoreCase(nuevoAlumnoDto.getCorreo()).ifPresent(e -> {
            throw new IllegalArgumentException("El correo " + nuevoAlumnoDto.getCorreo() + " ya está registrado.");
        });

        AlumnoEntity nuevoAlumno = new AlumnoEntity(nuevoAlumnoDto.getBoleta(), nuevoAlumnoDto.getNombre(), nuevoAlumnoDto.getCorreo(), nuevoAlumnoDto.getEdad());
        AlumnoEntity alumnoGuardado = alumnoRepository.save(nuevoAlumno);
        return new AlumnoDto(alumnoGuardado.getId(), alumnoGuardado.getBoleta(), alumnoGuardado.getNombre(), alumnoGuardado.getCorreo(), alumnoGuardado.getEdad());

    }
    @Override
    public List<AlumnoDto> findAll() {return List.of();}

    @Override
    public List<ReporteEdadDto> reportePorEdad() {
        return alumnoRepository.reportePorEdad();
    }
}
