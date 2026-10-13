package com.upiiz.entrenador.entrenadores;

import com.upiiz.entrenador.entrenadores.dto.EntrenadorDto;
import com.upiiz.entrenador.entrenadores.dto.NuevoEntrenadorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entrenadores")
public class EntrenadorController {
    private EntrenadorServiceImpl entrenadorService;

    public EntrenadorController(EntrenadorServiceImpl entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @PostMapping
    public ResponseEntity<EntrenadorDto> crear(@RequestBody NuevoEntrenadorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entrenadorService.save(dto));
    }

    @GetMapping
    public List<EntrenadorDto> listar() {
        return entrenadorService.findAll();
    }

    @GetMapping("/{id}")
    public EntrenadorDto obtener(@PathVariable Long id) {
        return entrenadorService.findById(id);
    }

    @PutMapping("/{id}")
    public EntrenadorDto actualizar(@PathVariable Long id, @RequestBody NuevoEntrenadorDto dto) {
        return entrenadorService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        entrenadorService.delete(id);
    }
}
