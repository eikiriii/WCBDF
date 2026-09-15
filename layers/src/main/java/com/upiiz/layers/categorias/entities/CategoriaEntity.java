package com.upiiz.layers.categorias.entities;

import jakarta.persistence.*;
// Capa de Base de Datos
@Entity
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    public CategoriaEntity(){

    }

    public CategoriaEntity(Long id, String descripcion){
        this.descripcion = descripcion;
        this.id = id;
    }

    public CategoriaEntity(String descripcion){
        this.descripcion = descripcion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
