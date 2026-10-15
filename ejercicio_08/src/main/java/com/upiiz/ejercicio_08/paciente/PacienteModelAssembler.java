package com.upiiz.ejercicio_08.paciente;

import com.upiiz.ejercicio_08.paciente.dto.PacienteDto;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PacienteModelAssembler implements RepresentationModelAssembler<PacienteDto, PacienteDto> {
    @Override
    public PacienteDto toModel(PacienteDto paciente){
        paciente.add(

                linkTo(methodOn(PacienteController.class).obtener(paciente.getId())).withSelfRel(),
                // link "update"
                linkTo(methodOn(PacienteController.class).actualizar(paciente.getId(), null)).withRel("update"),
                // link "delete"
                linkTo(methodOn(PacienteController.class).eliminar(paciente.getId())).withRel("delete"),
                // link listado "entrenadores"
                linkTo(methodOn(PacienteController.class).listar()).withRel("entrenadores"));
        return paciente;
    }

}
