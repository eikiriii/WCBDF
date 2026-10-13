package com.upiiz.entrenador.entrenadores;

import com.upiiz.entrenador.entrenadores.dto.EntrenadorDto;
import com.upiiz.entrenador.entrenadores.dto.NuevoEntrenadorDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;
    private final EntrenadorModelAssembler assembler; // para armar los links

    public EntrenadorController(EntrenadorService entrenadorService,
                                EntrenadorModelAssembler assembler) {
        this.entrenadorService = entrenadorService;
        this.assembler = assembler;
    }
    @PostMapping
    public ResponseEntity<EntrenadorDto> crear(@RequestBody NuevoEntrenadorDto dto) {
        EntrenadorDto model = assembler.toModel(entrenadorService.save(dto));
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @GetMapping
    public CollectionModel<EntrenadorDto> listar() {
        CollectionModel<EntrenadorDto> coleccion =
                assembler.toCollectionModel(entrenadorService.findAll());
        coleccion.add(linkTo(methodOn(EntrenadorController.class).listar()).withSelfRel());
        return coleccion;
    }

    @GetMapping("/{id}")
    public EntrenadorDto obtener(@PathVariable Long id) {
        return assembler.toModel(entrenadorService.findById(id));
    }

    @PutMapping("/{id}")
    public EntrenadorDto actualizar(@PathVariable Long id, @RequestBody NuevoEntrenadorDto dto) {
        return assembler.toModel(entrenadorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        entrenadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
