package com.upiiz.entrenador.entrenadores;

import com.upiiz.entrenador.entrenadores.dto.EntrenadorDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// aquí se agregan los links para las acciones
@Component
public class EntrenadorModelAssembler
        implements RepresentationModelAssembler<EntrenadorDto, EntrenadorDto> {

    @Override
    public EntrenadorDto toModel(EntrenadorDto entrenador) {
        entrenador.add(
                //link para "self"
                linkTo(methodOn(EntrenadorController.class).obtener(entrenador.getId())).withSelfRel(),
                // link "update"
                linkTo(methodOn(EntrenadorController.class).actualizar(entrenador.getId(), null)).withRel("update"),
                // link "delete"
                linkTo(methodOn(EntrenadorController.class).eliminar(entrenador.getId())).withRel("delete"),
                // link listado "entrenadores"
                linkTo(methodOn(EntrenadorController.class).listar()).withRel("entrenadores"));
        return entrenador;
    }
}
