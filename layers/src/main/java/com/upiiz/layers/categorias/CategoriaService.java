package com.upiiz.layers.categorias;

import com.upiiz.layers.categorias.dto.CategoriaDto;
import com.upiiz.layers.categorias.dto.NuevaCategoriaDto;

import java.util.List;

// Contrato - Entre Programadores
public interface CategoriaService {
    // jefe especifica los detalles del contrato, el desarrollador chambea

    // Guarde la Categoria
    CategoriaDto save(NuevaCategoriaDto nuevaCategoriaDto);
    // Muestre todas las categorias
    List<CategoriaDto> findAll();
    // Que actualice una categoria, dos parametros: a quien actualizamos y con que datos
    CategoriaDto update(Long id, NuevaCategoriaDto updateCategoriaDto);
    // Borre una categoria
    void delete(Long id);

}
