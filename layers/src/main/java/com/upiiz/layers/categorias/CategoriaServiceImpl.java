package com.upiiz.layers.categorias;

import com.upiiz.layers.categorias.dto.CategoriaDto;
import com.upiiz.layers.categorias.dto.NuevaCategoriaDto;
import com.upiiz.layers.categorias.entities.CategoriaEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
// Capa de negocios

@Service
public class CategoriaServiceImpl implements CategoriaService {


    CategoriaRepository categoriaRepository;

    CategoriaServiceImpl(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaDto save(NuevaCategoriaDto nuevaCategoriaDto) {
        //Reglas de negocios
        // Una de ellas que no se duplique el nombre de la categoria
        // No importar si usa mayusculas o minusculas
        // no se debe duplicar
        categoriaRepository.findByDescripcionIgnoreCase(nuevaCategoriaDto.getDescripcion()).ifPresent(e ->{
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe una categoria con la descripcion: " + nuevaCategoriaDto.getDescripcion());
        });

        CategoriaEntity nuevaCategoria = new CategoriaEntity(nuevaCategoriaDto.getDescripcion());
        CategoriaEntity categoriaGuardada = categoriaRepository.save(nuevaCategoria);
        return new CategoriaDto(categoriaGuardada.getId(), categoriaGuardada.getDescripcion());
    }

    @Override
    public List<CategoriaDto> findAll() {
        return List.of();
    }

    @Override
    public CategoriaDto update(Long id, NuevaCategoriaDto updateCategoriaDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
