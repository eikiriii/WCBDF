package com.upiiz.security.clientes;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    // definimos qué endpoints están libres
    @GetMapping
    public String getAll(){
        return "Todos los  clientes - Libre para todos";
    }
    @PostMapping
    public String nuevo(){
        return "Solo para todos los permitidos para CREATE";
    }

    @DeleteMapping()
    public String eliminarCliente(){
        return "Solo para usuarios con DELETE";
    }
}
