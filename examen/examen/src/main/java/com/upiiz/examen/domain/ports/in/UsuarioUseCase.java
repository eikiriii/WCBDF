package com.upiiz.examen.domain.ports.in;

import com.upiiz.examen.domain.models.Usuario;

import java.util.List;

public interface UsuarioUseCase {
    Usuario registrar(Usuario usuario);
    // Consultar la lista completa de usuarios.
    List<Usuario> listarTodos();
    // Consultar un usuario mediante su identificador.
    Usuario buscarPorId(Long id);
    // Modificar la información de un usuario.
    Usuario actualizar(Usuario usuario);
    //Eliminar un usuario
    void eliminar(Long id);
    List<Usuario> buscar(String texto);
}
