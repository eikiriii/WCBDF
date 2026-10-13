package com.upiiz.entrenador.entrenadores;

import com.upiiz.entrenador.entrenadores.dto.EntrenadorDto;
import com.upiiz.entrenador.entrenadores.dto.NuevoEntrenadorDto;

import java.util.List;

// Contrato - Entre Programadores, qué se va a hacer en el programa
public interface EntrenadorService {

    // Guarde la Categoria
    EntrenadorDto save(NuevoEntrenadorDto nuevoEntrenadorDto);
    // Muestre todas las categorias
    List<EntrenadorDto> findAll();
    EntrenadorDto findById(Long id);
    // Que actualice una categoria, dos parametros: a quien actualizamos y con que datos
    EntrenadorDto update(Long id, NuevoEntrenadorDto updateEntrenadorDto);
    // Borre una categoria
    void delete(Long id);
}
