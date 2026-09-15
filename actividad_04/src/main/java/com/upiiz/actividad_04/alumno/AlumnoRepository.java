package com.upiiz.actividad_04.alumno;

import com.upiiz.actividad_04.alumno.dto.ReporteEdadDto;
import com.upiiz.actividad_04.alumno.entities.AlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Long> {
    Optional<AlumnoEntity> findByBoleta(long boleta);
    Optional<AlumnoEntity> findByCorreoIgnoreCase(String correo);

    @Query("SELECT new com.upiiz.actividad_04.alumno.dto.ReporteEdadDto(a.edad, COUNT(a)) " +
            "FROM AlumnoEntity a GROUP BY a.edad ORDER BY a.edad")
    List<ReporteEdadDto> reportePorEdad();
}
