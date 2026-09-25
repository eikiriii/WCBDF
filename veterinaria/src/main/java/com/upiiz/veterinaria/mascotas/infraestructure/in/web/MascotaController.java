package com.upiiz.veterinaria.mascotas.infraestructure.in.web;

import com.upiiz.veterinaria.mascotas.domain.models.Mascota;
import com.upiiz.veterinaria.mascotas.domain.ports.in.MascotaUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mascotas")
public class MascotaController {
    private final MascotaUseCase mascotaUseCase;

    public MascotaController(final MascotaUseCase mascotaUseCase) {
        this.mascotaUseCase = mascotaUseCase;
    }

    @GetMapping
    public List<Mascota> getMascota(){
        return mascotaUseCase.listar();
    }

    @GetMapping("/{id}")
    public Mascota getMascota(@PathVariable Long id){
        return mascotaUseCase.buscarPorId(id);
    }

    @PostMapping
    public Mascota createMascota(@RequestBody final Mascota mascota){
        return mascotaUseCase.registrar(mascota);
    }

    @PutMapping("/{id}")
    public Mascota updateMascota(@PathVariable Long id, @RequestBody Mascota mascota){
        mascota.setId(id);
        return mascotaUseCase.actualizar(mascota);
    }

    @DeleteMapping("/{id}")
    public void deleteMascota(@PathVariable final Long id){
        mascotaUseCase.eliminar(id);
    }

}
