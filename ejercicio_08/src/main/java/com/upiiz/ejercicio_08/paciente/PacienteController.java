package com.upiiz.ejercicio_08.paciente;

import com.upiiz.ejercicio_08.paciente.dto.NuevoPacienteDto;
import com.upiiz.ejercicio_08.paciente.dto.PacienteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;
    private final PacienteModelAssembler assembler;

    public PacienteController(PacienteService pacienteService, PacienteModelAssembler assembler) {
        this.pacienteService = pacienteService;
        this.assembler = assembler;
    }

    @Operation(
            summary = "Obtiene una solicitud para agregar un paciente",
            description = "Retorna los datos guardados en la base de datos",
            tags = { "operaciones" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso creado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PacienteDto.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Conflicto (duplicado)", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del paciente que se desea crear",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = NuevoPacienteDto.class))
    )
    @PostMapping
    public ResponseEntity<PacienteDto> crear(@RequestBody NuevoPacienteDto dto) {
        PacienteDto model = assembler.toModel(pacienteService.save(dto));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @Operation(
            summary = "Obtiene una solicitud para obtener todos los pacientes",
            description = "Retorna los datos guardados en la base de datos",
            tags = { "solicitudes" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PacienteDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @GetMapping()
    public CollectionModel<PacienteDto> listar() {
        CollectionModel<PacienteDto> coleccion =
                assembler.toCollectionModel(pacienteService.FindAll());
        coleccion.add(linkTo(methodOn(PacienteController.class).listar()).withSelfRel());
        return coleccion;
    }

    @Operation(
            summary = "Obtiene una solicitud por ID",
            description = "Retorna los datos asociados a la solicitud consultada",
            tags = { "solicitudes" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PacienteDto.class))),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public PacienteDto obtener(
            @Parameter(description = "ID del paciente", required = true, example = "1")
            @PathVariable Long id) {
        return assembler.toModel(pacienteService.FindById(id));
    }

    @Operation(
            summary = "Obtiene una solicitud para actualizar los datos de un paciente",
            description = "Retorna los datos actualizados en la base de datos",
            tags = { "operaciones" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso actualizado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PacienteDto.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Paciente no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Nuevos datos del paciente a actualizar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = NuevoPacienteDto.class))
    )
    @PutMapping("/{id}")
    public PacienteDto actualizar(
            @Parameter(description = "ID del paciente", required = true, example = "1")
            @PathVariable Long id,
            @RequestBody NuevoPacienteDto dto) {
        return assembler.toModel(pacienteService.update(id, dto));
    }

    @Operation(
            summary = "Obtiene una solicitud para eliminar un paciente",
            description = "No retorna ningún valor",
            tags = { "operaciones" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Eliminación exitosa", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Paciente no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del paciente", required = true, example = "1")
            @PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}