package com.example.citasmedicas.model;

public class Paciente {

    private Long id;
    private String nombre;
    private String telefono;

    public Paciente(Long id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
}
