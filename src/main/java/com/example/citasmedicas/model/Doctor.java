package com.example.citasmedicas.model;

public class Doctor {

    private Long id;
    private String nombre;
    private String especialidad;

    public Doctor(Long id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}