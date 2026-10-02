package com.upiiz.practicaII.infraestructure.in;

import com.upiiz.practicaII.domain.models.Heroe;
import com.upiiz.practicaII.domain.ports.in.HeroeUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/heroes")
public class HeroeController {
    private final HeroeUseCase heroeUseCase;

    public HeroeController(HeroeUseCase heroeUseCase) {
        this.heroeUseCase = heroeUseCase;
    }

    @GetMapping
    public List<Heroe> getHeroes() {
        return heroeUseCase.listarTodos();
    }

    @GetMapping("/{id}")
    public Heroe getHeroe(@PathVariable Long id) {
        return heroeUseCase.buscarPorId(id);
    }

    @GetMapping("/epoca/{epoca}")
    public List<Heroe> findByEpoca(@PathVariable String epoca) {
        return heroeUseCase.buscarPorEpoca(epoca);
    }

    @GetMapping("/movimiento/{movimiento}")
    public List<Heroe> findByMovimiento(@PathVariable String movimiento) {
        return heroeUseCase.buscarPorMovimiento(movimiento);
    }

    @GetMapping("/estado/{estado}")
    public List<Heroe> findByEstado(@PathVariable String estado) {
        return heroeUseCase.buscarPorEstado(estado);
    }

    @PostMapping
    public Heroe createHeroe(@RequestBody Heroe heroe) {
        return heroeUseCase.registrar(heroe);
    }

    @PutMapping("/{id}")
    public Heroe updateHeroe(@PathVariable Long id, @RequestBody Heroe heroe) {
        heroe.setId(id);
        return heroeUseCase.actualizar(heroe);
    }

    @DeleteMapping("/{id}")
    public void deleteHeroe(@PathVariable Long id) {
        heroeUseCase.eliminar(id);
    }
}