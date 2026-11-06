package com.upiiz.security.controllers;

import com.upiiz.security.assemblers.LibroModelAssembler;
import com.upiiz.security.entities.Libro;
import com.upiiz.security.services.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/api/v1/libros")
@RestController
public class LibroController {

    private LibroService libroService;
    private final LibroModelAssembler assembler;

    public LibroController(LibroService libroService, LibroModelAssembler assembler) {
        this.libroService = libroService;
        this.assembler = assembler;
    }


    @Operation(
            summary = "Obtiene una solicitud para agregar un libro",
            description = "Retorna los datos guardados en la base de datos",
            tags = { "CRUD - Libros" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Libro registrado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Libro.class)))),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Libro ya registrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @PostMapping("/")
    public ResponseEntity<?> registrar(@RequestBody Libro libro) {
        //Verificar que no exista otro nombre de usuario igual
        if (libroService.getLibroByTitulo(libro.getTitulo()).isPresent()) {
            return ResponseEntity.status(409).body(
                    Map.of("estado", 0, "mensaje", "El libro ya existe"));
        }
        Libro guardado = libroService.saveLibro(libro);
        EntityModel<Libro> model = assembler.toModel(guardado);

        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())   // header Location
                .body(Map.of("estado", 1, "mensaje", "libro registrado", "libro", model));
    }

    @Operation(
            summary = "Obtiene una solicitud para actualizar un libro determinado",
            description = "Retorna los datos guardados en la base de datos",
            tags = { "CRUD - Libros" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Libro actualizado",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Libro.class)))),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Libro libro){
        if (libroService.getLibroById(id).isPresent()) {
            Libro actualizado = libroService.updateLibro(id, libro);
            return ResponseEntity.ok(
                    Map.of("estado", 1, "mensaje", "libro actualizado",
                            "libro", assembler.toModel(actualizado)));
        }
        return ResponseEntity.status(404).body(
                Map.of("estado", 0, "mensaje", "No existe libro con ese ID"));
    }

    @Operation(
            summary = "Obtiene una solicitud para eliminar un libro determinado",
            description = "No retorna ningún valor",
            tags = { "CRUD - Libros" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Eliminación exitosa", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Libro no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        if (libroService.getLibroById(id).isPresent()) {
            libroService.deleteLibro(id);
            // Tras eliminar, el enlace útil es volver a la colección
            return ResponseEntity.ok(
                    Map.of("estado", 1, "mensaje", "libro eliminado",
                            "_links", Map.of("libros", Map.of("href",
                                    linkTo(methodOn(LibroController.class).allLibros()).toUri().toString()))));
        }
        return ResponseEntity.status(404).body(
                Map.of("estado", 0, "mensaje", "No existe libro con ese ID"));
    }

    @Operation(
            summary = "Obtiene una solicitud para obtener una lista con todos los libros",
            description = "Retorna los datos guardados en la base de datos",
            tags = { "CRUD - Libros" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Libro.class)))),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @GetMapping("/lista_libros")
    public ResponseEntity<CollectionModel<EntityModel<Libro>>> allLibros() {
        List<Libro> libros = libroService.allLibros();
        return ResponseEntity.ok(
                assembler.toCollectionModel(libros)
                        .add(linkTo(methodOn(LibroController.class).allLibros()).withSelfRel()));
    }

    @Operation(
            summary = "Obtiene una solicitud por ID",
            description = "Retorna los datos asociados a la solicitud consultada",
            tags = { "CRUD - Libros" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Libro.class))),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @GetMapping("/lista_libros/{id}")
    public ResponseEntity<?> FindById(@PathVariable Long id) {
        return libroService.getLibroById(id)
                .<ResponseEntity<?>>map(libro -> ResponseEntity.ok(
                        Map.of("estado", 1, "mensaje", "libro encontrado",
                                "libro", assembler.toModel(libro))))
                .orElseGet(() -> ResponseEntity.status(404).body(
                        Map.of("estado", 0, "mensaje", "No existe libro con ese ID")));
    }

}
