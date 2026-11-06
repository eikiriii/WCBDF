package com.upiiz.security.controllers;

import com.upiiz.security.entities.Libro;
import com.upiiz.security.entities.User;
import com.upiiz.security.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/libros/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "Obtiene una solicitud por inicio de sesión",
            description = "Retorna los datos asociados al usuario",
            tags = { "CRUD - Usuarios" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // solo devuelve algo si es token es valido
        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }


    // aquí solo van a entrar los ADMIN
    @Operation(
            summary = "Obtiene una solicitud por listar todos los usuarios",
            description = "Retorna los datos asociados a la solicitud consultada",
            tags = { "CRUD - Usuarios" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
