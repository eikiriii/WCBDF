package com.upiiz.examen.domain.ports.out;

import com.upiiz.examen.domain.models.Usuario;

import java.util.List;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    // Consultar la lista completa de usuarios.
    List<Usuario> findAll();
    // Consultar un usuario mediante su identificador.
    Usuario findById(Long id);
    // Modificar la información de un usuario.
    Usuario update(Usuario usuario);
    //Eliminar un usuario
    void delete(Long id);
    List<Usuario> buscar(String texto);
    boolean existsByUsuarioIgnoreCase(String usuario);
    boolean existsByUsuarioIgnoreCaseAndIdNot(String usuario, Long id);

    boolean existsByCorreoIgnoreCase(String correo);
    boolean existsByCorreoIgnoreCaseAndIdNot(String correo, Long id);
}
