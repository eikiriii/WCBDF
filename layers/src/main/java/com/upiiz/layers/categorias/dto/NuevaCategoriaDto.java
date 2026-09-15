package com.upiiz.layers.categorias.dto;
// recibir datos del cliente
public class NuevaCategoriaDto {

    private String descripcion;

    public NuevaCategoriaDto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
