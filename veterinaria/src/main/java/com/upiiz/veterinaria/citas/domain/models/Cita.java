package com.upiiz.veterinaria.citas.domain.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Cita {
    private Long id;
    private LocalDateTime fechaHora;
    private String Mascota;
    private String Cliente;

    public Cita() {
    }

    public Cita(Long id, String cliente, String mascota, LocalDateTime fechaHora) {
        this.id = id;
        Cliente = cliente;
        Mascota = mascota;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMascota() {
        return Mascota;
    }

    public void setMascota(String mascota) {
        Mascota = mascota;
    }
}
