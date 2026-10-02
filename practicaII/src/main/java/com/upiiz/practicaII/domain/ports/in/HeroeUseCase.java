package com.upiiz.practicaII.domain.ports.in;

import com.upiiz.practicaII.domain.models.Heroe;

import java.util.List;

public interface HeroeUseCase {
    // Registrar nuevos héroes
    Heroe registrar(Heroe heroe);
    // Consultar la lista completa de héroes.
    List<Heroe> listarTodos();
    // Buscar héroes por época histórica
    List<Heroe> buscarPorEpoca(String epoca);
    // Buscar héroes por movimiento
    List<Heroe> buscarPorMovimiento(String movimiento);
    // Buscar héroes por estado de nacimiento.
    List<Heroe> buscarPorEstado(String estado);
    // Consultar un héroe mediante su identificador.
    Heroe buscarPorId(Long id);
    // Modificar la información de un héroe.
    Heroe actualizar(Heroe heroe);
    //Eliminar un héroe
    void eliminar(Long id);
}
