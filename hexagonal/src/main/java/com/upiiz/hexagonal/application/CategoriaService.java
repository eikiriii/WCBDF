package com.upiiz.hexagonal.application;

import com.upiiz.hexagonal.domain.models.Categoria;
import com.upiiz.hexagonal.domain.ports.in.CategoriaUseCase;
import com.upiiz.hexagonal.domain.ports.out.CategoriaRepository;

import java.util.List;

public class CategoriaService implements CategoriaUseCase {
    //Reglas de Negocio

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria registrar(Categoria categoria) {
        // validar que los datos estén correcto
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria actualizar(Categoria categoria) {
        return categoriaRepository.update(categoria);
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepository.delete(id);
    }
}
