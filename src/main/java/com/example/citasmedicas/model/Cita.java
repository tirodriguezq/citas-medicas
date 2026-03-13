package com.example.citasmedicas.model;

public class Cita {

    private Long id;
    private String nombrePaciente;
    private String doctor;
    private String fecha;
    private String estado;

    public Cita(Long id, String nombrePaciente, String doctor, String fecha, String estado) {
        this.id = id;
        this.nombrePaciente = nombrePaciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }
}
