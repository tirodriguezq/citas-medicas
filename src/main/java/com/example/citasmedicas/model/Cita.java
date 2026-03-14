package com.example.citasmedicas.model;

public class Cita {

    private Long id;
    private String nombrePaciente;
    private String doctor;
    private String fecha;
    private String estado;

    public Cita() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}