package com.upiiz.actividad_04.alumno.dto;

public class ReporteEdadDto {
    private int edad;
    private long cantidad;

    public ReporteEdadDto(int edad, long cantidad) {
        this.edad = edad;
        this.cantidad = cantidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }
}
