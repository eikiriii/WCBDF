package com.upiiz.layers.categorias;

import com.upiiz.layers.categorias.dto.CategoriaDto;
import com.upiiz.layers.categorias.dto.NuevaCategoriaDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Capa de Presentacion - {JSON}, Navegador, Cliente REST(Insomnia, PostMan
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private CategoriaServiceImpl categoriaService;

    CategoriaController(CategoriaServiceImpl categoriaService){
        this.categoriaService = categoriaService;
    }
    // Varios endPoints o rutas
    // Get de todos
    // Get de solo una
    // Post de una categoria}
    @PostMapping()
    public CategoriaDto save(@RequestBody NuevaCategoriaDto nuevaCategoriaDto){
        return categoriaService.save(nuevaCategoriaDto);

    }
    // Put de una categoria
    // Delete de una categoria 

}
