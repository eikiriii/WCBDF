package com.upiiz.hexagonal.infraestructure.out.persistence;

import com.upiiz.hexagonal.domain.models.Categoria;
import com.upiiz.hexagonal.domain.ports.out.CategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CategoriaRepositoryImpl implements CategoriaRepository {
    private final CategoriaRepositoryJpa categoriaRepositoryJpa;

    public CategoriaRepositoryImpl(CategoriaRepositoryJpa categoriaRepositoryJpa) {
        this.categoriaRepositoryJpa = categoriaRepositoryJpa;
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity categoriaEntity = new CategoriaEntity(null, categoria.getDescripcion());
        CategoriaEntity categoriaGuardada = categoriaRepositoryJpa.save(categoriaEntity);
        return new Categoria(categoriaGuardada.getId(),categoriaGuardada.getDescripcion());
    }

    @Override
    public Categoria findById(Long id) {
        return null;
    }

    @Override
    public List<Categoria> findAll() {
        return List.of();
    }

    @Override
    public Categoria update(Categoria categoria) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
