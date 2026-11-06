package com.upiiz.security.repositories;

import com.upiiz.security.entities.Libro;
import com.upiiz.security.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends CrudRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);
}
