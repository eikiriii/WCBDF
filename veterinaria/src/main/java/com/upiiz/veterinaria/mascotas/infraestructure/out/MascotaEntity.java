package com.upiiz.veterinaria.mascotas.infraestructure.out;

import jakarta.persistence.*;

@Entity
@Table(name="mascota")
public class MascotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreMascota;
    private String nombreDueno;
    private String especie;
    private String raza;
    private int edad;

    public MascotaEntity() {
    }

    public MascotaEntity(Long id, String nombreMascota, String nombreDueno, String especie, String raza, int edad) {
        this.id = id;
        this.nombreMascota = nombreMascota;
        this.nombreDueno = nombreDueno;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
