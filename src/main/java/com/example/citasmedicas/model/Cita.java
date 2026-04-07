package com.example.citasmedicas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("CITAS")
public class Cita {

    @Id
    private Long id;

    @Column("PACIENTE_ID")
    private Long pacienteId;

    @Column("DOCTOR_ID")
    private Long doctorId;

    private LocalDate fecha;
    private String estado;

    public Cita() {
    }

    public Cita(Long id, Long pacienteId, Long doctorId, LocalDate fecha, String estado) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.doctorId = doctorId;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}