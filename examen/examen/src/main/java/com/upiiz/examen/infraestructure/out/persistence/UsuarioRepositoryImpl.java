package com.upiiz.examen.infraestructure.out.persistence;

import com.upiiz.examen.domain.models.Usuario;
import com.upiiz.examen.domain.ports.out.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final UsuarioRepositoryJpa usuarioRepositoryJpa;

    public UsuarioRepositoryImpl(UsuarioRepositoryJpa usuarioRepositoryJpa) {
        this.usuarioRepositoryJpa = usuarioRepositoryJpa;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = toEntity(usuario);
        UsuarioEntity usuarioGuardado = usuarioRepositoryJpa.save(usuarioEntity);
        return toDomain(usuarioGuardado);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepositoryJpa.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepositoryJpa.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    @Override
    public Usuario update(Usuario usuario) {
        UsuarioEntity usuarioEntity = toEntity(usuario);
        UsuarioEntity usuarioActualizado = usuarioRepositoryJpa.save(usuarioEntity);
        return toDomain(usuarioActualizado);
    }

    @Override
    public void delete(Long id) {
        usuarioRepositoryJpa.deleteById(id);
    }

    @Override
    public List<Usuario> buscar(String texto) {
        return usuarioRepositoryJpa.buscar(texto)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public boolean existsByUsuarioIgnoreCase(String usuario) {
        return usuarioRepositoryJpa.existsByUsuarioIgnoreCase(usuario);
    }

    @Override
    public boolean existsByUsuarioIgnoreCaseAndIdNot(String usuario, Long id) {
        return usuarioRepositoryJpa.existsByUsuarioIgnoreCaseAndIdNot(usuario, id);
    }

    @Override
    public boolean existsByCorreoIgnoreCase(String correo) {
        return usuarioRepositoryJpa.existsByCorreoIgnoreCase(correo);
    }

    @Override
    public boolean existsByCorreoIgnoreCaseAndIdNot(String correo, Long id) {
        return usuarioRepositoryJpa.existsByCorreoIgnoreCaseAndIdNot(correo, id);
    }

    private UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido_paterno(),
                usuario.getApellido_materno(),
                usuario.getCorreo(),
                usuario.getUsuario(),
                usuario.getPassword(),
                usuario.getFecha_nacimiento()
        );
    }

    private Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido_paterno(),
                entity.getApellido_materno(),
                entity.getCorreo(),
                entity.getUsuario(),
                entity.getPassword(),
                entity.getFecha_nacimiento()
        );
    }
}
