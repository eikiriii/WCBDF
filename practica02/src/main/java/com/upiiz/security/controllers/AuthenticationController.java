package com.upiiz.security.controllers;

import com.upiiz.security.dtos.LoginUserDto;
import com.upiiz.security.dtos.RegisterUserDto;
import com.upiiz.security.entities.Libro;
import com.upiiz.security.entities.User;
import com.upiiz.security.responses.LoginResponse;
import com.upiiz.security.services.AuthenticationService;
import com.upiiz.security.services.JwtService;
import com.upiiz.security.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/v1/libros/security")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }
    @Operation(
            summary = "Obtiene una solicitud para agregar un usuario",
            description = "Retorna los datos guardados en la base de datos",
            tags = { "CRUD - Autenticación" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Libro registrado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = RegisterUserDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Correo ya registrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) {
        if(userService.getUserByEmail(registerUserDto.getEmail()).isPresent()){
            return ResponseEntity.status(409).body(
                    Map.of("estado",0,
                            "mensaje", "El correo ya está registrado")
            );
        }
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.status(201).body(
                Map.of("estado",1,
                        "mensaje", "usuario registrado",
                        "usuario", registeredUser)
        );
    }

    @Operation(
            summary = "Obtiene una solicitud para iniciar sesión",
            description = "Retorna un token de autenticación y el tiempo de duración",
            tags = { "CRUD - Autenticación" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Libro registrado con éxito",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LoginUserDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Parámetro inválido", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Correo ya registrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        // se regresa el token y el tiempo de expiracion
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
