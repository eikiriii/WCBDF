package com.upiiz.actividad_04.alumno.dto;

public class NuevoAlumnoDto {

    private Long boleta;
    private String nombre;
    private String correo;
    private int edad;

    public NuevoAlumnoDto(String nombre, Long boleta, String correo, int edad) {
        this.nombre = nombre;
        this.boleta = boleta;
        this.correo = correo;
        this.edad = edad;
    }

    public Long getBoleta() {
        return boleta;
    }

    public void setBoleta(Long boleta) {
        this.boleta = boleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
