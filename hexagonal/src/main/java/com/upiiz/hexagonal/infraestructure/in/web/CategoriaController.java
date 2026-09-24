package com.upiiz.hexagonal.infraestructure.in.web;

import com.upiiz.hexagonal.application.CategoriaService;
import com.upiiz.hexagonal.domain.models.Categoria;
import com.upiiz.hexagonal.domain.ports.in.CategoriaUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categorias") // donde vamos a estar exponiendo las solicitudes
public class CategoriaController {
    // traer casos de uso que estan implementados en la application
    private final CategoriaUseCase categoriaService;
    public CategoriaController (final CategoriaUseCase categoriaService){
        this.categoriaService = categoriaService;
    }
    @GetMapping
    public List<Categoria> getCategoria(){
        return categoriaService.listar();
    }

    @GetMapping("/{id}")
    public Categoria getCategoria(@PathVariable Long id){
        return categoriaService.buscarPorId(id);
    }

    @PostMapping
    public Categoria createCategoria(@RequestBody final Categoria categoria){
        return categoriaService.registrar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria updateCategoria(@RequestBody Categoria categoria){
        return categoriaService.actualizar(categoria);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable final Long id){
        categoriaService.eliminar(id);
    }

}
