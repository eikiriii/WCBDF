package com.upiiz.practicaII.domain.ports.out;

import com.upiiz.practicaII.domain.models.Heroe;

import java.util.List;

public interface HeroeRepository {
    // Registrar nuevos héroes
    Heroe save(Heroe heroe);
    // Consultar la lista completa de héroes.
    List<Heroe> findAll();
    // Buscar héroes por época histórica
    List<Heroe> findByEpoca(String epoca);
    // Buscar héroes por movimiento
    List<Heroe> findByMovimiento(String movimiento);
    // Buscar héroes por estado de nacimiento.
    List<Heroe> findByEstado(String estado);
    // Consultar un héroe mediante su identificador.
    Heroe findById(Long id);
    // Modificar la información de un héroe.
    Heroe update(Heroe heroe);
    //Eliminar un héroe
    void delete(Long id);
}
