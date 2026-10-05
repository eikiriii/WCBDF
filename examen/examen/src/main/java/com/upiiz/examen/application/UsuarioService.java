package com.upiiz.examen.application;

import com.upiiz.examen.domain.models.Usuario;
import com.upiiz.examen.domain.ports.in.UsuarioUseCase;
import com.upiiz.examen.domain.ports.out.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

public class UsuarioService implements UsuarioUseCase {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        validarDatosObligatorios(usuario);
        validarFechaNacimiento(usuario.getFecha_nacimiento());
        validarUsuarioOCorreoDuplicado(usuario.getUsuario(), usuario.getCorreo(), usuario.getId());
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        Usuario existente = usuarioRepository.findById(usuario.getId());
        if (existente == null) {
            throw new IllegalArgumentException("No se puede modificar un usuario que no existe.");
        }
        validarDatosObligatorios(usuario);
        validarFechaNacimiento(usuario.getFecha_nacimiento());
        validarUsuarioOCorreoDuplicado(usuario.getUsuario(), usuario.getCorreo(), usuario.getId());
        return usuarioRepository.update(usuario);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.delete(id);
    }

    @Override
    public List<Usuario> buscar(String texto) {
        if (texto == null || texto.trim().length() < 3) {
            return List.of();
        }
        return usuarioRepository.buscar(texto.trim());
    }

    private void validarDatosObligatorios(Usuario usuario) {
        // el nombre es obligatorio
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (usuario.getApellido_materno() == null || usuario.getApellido_materno().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido materno es obligatorio.");
        }
        if (usuario.getApellido_paterno() == null || usuario.getApellido_paterno().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido paterno es obligatorio.");
        }
        if (usuario.getFecha_nacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        if (usuario.getUsuario() == null || usuario.getUsuario().trim().length() < 5) {
            throw new IllegalArgumentException("El usuario debería tener al menos 5 caracteres");
        }
        if (usuario.getPassword() == null || usuario.getPassword().trim().length() < 8) {
            throw new IllegalArgumentException("La contraseña debería tener al menos 8 caracteres");
        }
    }
    private void validarFechaNacimiento(LocalDate fechaNacimiento) {
        // Regla 4: la fecha de nacimiento no puede ser posterior a la fecha actual
        if (fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior a la fecha actual.");
        }
    }
    private void validarUsuarioOCorreoDuplicado(String usuario, String correo, Long idActual) {
        boolean usuarioDuplicado = (idActual == null)
                ? usuarioRepository.existsByUsuarioIgnoreCase(usuario)
                : usuarioRepository.existsByUsuarioIgnoreCaseAndIdNot(usuario, idActual);

        if (usuarioDuplicado) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con ese nombre de usuario.");
        }

        boolean correoDuplicado = (idActual == null)
                ? usuarioRepository.existsByCorreoIgnoreCase(correo)
                : usuarioRepository.existsByCorreoIgnoreCaseAndIdNot(correo, idActual);

        if (correoDuplicado) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con ese correo.");
        }
    }
    
}
