package com.upiiz.actividad_04.alumno;

import com.upiiz.actividad_04.alumno.dto.AlumnoDto;
import com.upiiz.actividad_04.alumno.dto.NuevoAlumnoDto;
import com.upiiz.actividad_04.alumno.AlumnoServiceImpl;
import com.upiiz.actividad_04.alumno.dto.ReporteEdadDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private AlumnoServiceImpl alumnoService;

    public AlumnoController(AlumnoServiceImpl alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping()
    public AlumnoDto save(@RequestBody NuevoAlumnoDto nuevoAlumnoDto){
        return alumnoService.save(nuevoAlumnoDto);

    }

    @GetMapping("/reporte-por-edad")
    public List<ReporteEdadDto> reportePorEdad() {
        return alumnoService.reportePorEdad();
    }
}
