package com.upiiz.practicaII.application;

import com.upiiz.practicaII.domain.models.Heroe;
import com.upiiz.practicaII.domain.ports.in.HeroeUseCase;
import com.upiiz.practicaII.domain.ports.out.HeroeRepository;

import java.time.LocalDate;
import java.util.List;

public class HeroeService implements HeroeUseCase {

    private HeroeRepository heroeRepository;

    public HeroeService(HeroeRepository heroeRepository) {
        this.heroeRepository = heroeRepository;
    }

    @Override
    public Heroe registrar(Heroe heroe) {
        validarDatosObligatorios(heroe);
        validarFechaNacimiento(heroe.getFechaNacimiento());
        validarNombreApellidoDuplicado(heroe.getNombre(), heroe.getApellido(), null);
        return heroeRepository.save(heroe);
    }

    @Override
    public List<Heroe> listarTodos() {
        return heroeRepository.findAll();
    }

    @Override
    public List<Heroe> buscarPorEpoca(String epoca) {
        return heroeRepository.findByEpoca(epoca);
    }

    @Override
    public List<Heroe> buscarPorMovimiento(String movimiento) {
        return heroeRepository.findByMovimiento(movimiento);
    }

    @Override
    public List<Heroe> buscarPorEstado(String estado) {
        return heroeRepository.findByEstado(estado);
    }

    @Override
    public Heroe buscarPorId(Long id) {
        return heroeRepository.findById(id);
    }

    @Override
    public Heroe actualizar(Heroe heroe) {
        // Regla 9: no se puede modificar un héroe que no exista
        Heroe existente = heroeRepository.findById(heroe.getId());
        if (existente == null) {
            throw new IllegalArgumentException("No se puede modificar un héroe que no existe.");
        }

        validarDatosObligatorios(heroe);
        validarFechaNacimiento(heroe.getFechaNacimiento());
        validarNombreApellidoDuplicado(heroe.getNombre(), heroe.getApellido(), heroe.getId());

        return heroeRepository.update(heroe);
    }

    @Override
    public void eliminar(Long id) {
        // Regla 10: no se puede eliminar un héroe que no exista
        Heroe existente = heroeRepository.findById(id);
        if (existente == null) {
            throw new IllegalArgumentException("No se puede eliminar un héroe que no existe.");
        }
        heroeRepository.delete(id);
    }

    // ---------- Métodos de validación (reglas de negocio) ----------

    private void validarDatosObligatorios(Heroe heroe) {
        // Regla 1: el nombre es obligatorio
        if (heroe.getNombre() == null || heroe.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        // Regla 2: el apellido es obligatorio
        if (heroe.getApellido() == null || heroe.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        // Regla 3: la fecha de nacimiento es obligatoria
        if (heroe.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        // Regla 6: el estado de nacimiento es obligatorio
        if (heroe.getEstadoNacimiento() == null || heroe.getEstadoNacimiento().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de nacimiento es obligatorio.");
        }
        // Regla 7: la época histórica es obligatoria
        if (heroe.getEpoca() == null || heroe.getEpoca().trim().isEmpty()) {
            throw new IllegalArgumentException("La época histórica es obligatoria.");
        }
        // Regla 8: el movimiento es obligatorio
        if (heroe.getMovimiento() == null || heroe.getMovimiento().trim().isEmpty()) {
            throw new IllegalArgumentException("El movimiento es obligatorio.");
        }
    }

    private void validarFechaNacimiento(LocalDate fechaNacimiento) {
        // Regla 4: la fecha de nacimiento no puede ser posterior a la fecha actual
        if (fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior a la fecha actual.");
        }
    }

    private void validarNombreApellidoDuplicado(String nombre, String apellido, Long idActual) {
        // Regla 5: no se permitirá registrar dos héroes con el mismo nombre y apellido
        boolean existeDuplicado = heroeRepository.findAll().stream()
                .anyMatch(h -> h.getNombre().equalsIgnoreCase(nombre)
                        && h.getApellido().equalsIgnoreCase(apellido)
                        && (idActual == null || !h.getId().equals(idActual)));

        if (existeDuplicado) {
            throw new IllegalArgumentException("Ya existe un héroe registrado con el mismo nombre y apellido.");
        }
    }
}
