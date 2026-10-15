package com.upiiz.ejercicio_08.paciente.dto;

public class NuevoPacienteDto {
    private String nombre;
    private int edad;
    private String genero;
    private String telefono;
    private boolean seguro;

    public NuevoPacienteDto(String nombre, int edad, String genero, String telefono, boolean seguro) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.seguro = seguro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }
}
