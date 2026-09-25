package com.upiiz.veterinaria.consultas.domain.models;

import java.time.LocalDate;

public class Consulta {
    private Long id;
    private LocalDate fecha;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private double costo;

}
