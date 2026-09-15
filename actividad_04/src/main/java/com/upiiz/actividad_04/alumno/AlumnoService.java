package com.upiiz.actividad_04.alumno;

import com.upiiz.actividad_04.alumno.dto.AlumnoDto;
import com.upiiz.actividad_04.alumno.dto.NuevoAlumnoDto;
import com.upiiz.actividad_04.alumno.dto.ReporteEdadDto;

import java.util.List;

public interface AlumnoService {
    AlumnoDto save(NuevoAlumnoDto nuevoAlumnoDto);
    List<AlumnoDto> findAll();
    List<ReporteEdadDto> reportePorEdad();
}
