package com.upiiz.security.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.upiiz.security.controllers.LibroController;
import com.upiiz.security.entities.Libro;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LibroModelAssembler implements RepresentationModelAssembler<Libro, EntityModel<Libro>> {

    @Override
    public EntityModel<Libro> toModel(Libro libro) {
        EntityModel<Libro> model = EntityModel.of(libro,
                linkTo(methodOn(LibroController.class).FindById(libro.getId())).withSelfRel(),
                linkTo(methodOn(LibroController.class).allLibros()).withRel("libros"));

        // Las acciones de escritura solo se ofrecen si hay un usuario autenticado
        if (estaAutenticado()) {
            model.add(
                    linkTo(methodOn(LibroController.class).actualizar(libro.getId(), null)).withRel("actualizar"),
                    linkTo(methodOn(LibroController.class).eliminar(libro.getId())).withRel("eliminar"));
        }
        return model;
    }

    private boolean estaAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
    }
}