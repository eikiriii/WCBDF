package com.upiiz.hexagonal.domain.ports.in;

import com.upiiz.hexagonal.domain.models.Categoria;

import java.util.List;

//Interfaz = Contratos = Puerto de Entrada
public interface CategoriaUseCase {
    Categoria registrar(Categoria categoria);
    List<Categoria> listar();
    Categoria buscarPorId(Long id);
    Categoria actualizar(Categoria categoria);
    void eliminar(Long id);
}
