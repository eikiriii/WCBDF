package com.upiiz.layers.categorias;
import com.upiiz.layers.categorias.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Capa de Persistencia - ORM - JPA <-!!  - Mongose, TypeORM, Squalize
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Long> {
    // Agregar mas metodos
    Optional<CategoriaEntity> findByDescripcionIgnoreCase(String descripcion);
}
