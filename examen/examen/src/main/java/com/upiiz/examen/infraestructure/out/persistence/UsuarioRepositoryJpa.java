package com.upiiz.examen.infraestructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity,Long> {
    boolean existsByUsuarioIgnoreCase(String usuario);
    boolean existsByUsuarioIgnoreCaseAndIdNot(String usuario, Long id);
    boolean existsByCorreoIgnoreCase(String correo);
    boolean existsByCorreoIgnoreCaseAndIdNot(String correo, Long id);
    @Query("""
        SELECT u FROM UsuarioEntity u
        WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(u.apellido_paterno) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(u.apellido_materno) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(u.usuario) LIKE LOWER(CONCAT('%', :texto, '%'))
        """)
    List<UsuarioEntity> buscar(@Param("texto") String texto);
}
