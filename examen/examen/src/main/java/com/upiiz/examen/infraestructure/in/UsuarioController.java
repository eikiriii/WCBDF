package com.upiiz.examen.infraestructure.in;

import com.upiiz.examen.domain.models.Usuario;
import com.upiiz.examen.domain.ports.in.UsuarioUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioUseCase usuarioUseCase;

    public UsuarioController(UsuarioUseCase usuarioUseCase) {
        this.usuarioUseCase = usuarioUseCase;
    }

    @GetMapping
    public List<Usuario> getUsuarios(){
        return usuarioUseCase.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        return usuarioUseCase.buscarPorId(id);
    }
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioUseCase.registrar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        usuario.setId(id);
        return usuarioUseCase.actualizar(usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){
        usuarioUseCase.eliminar(id);
    }

    @GetMapping("/buscar")
    public List<Usuario> buscarUsuarios(@RequestParam String texto) {
        return usuarioUseCase.buscar(texto);
    }
}
