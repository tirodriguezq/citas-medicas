package com.example.citasmedicas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("CITAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    private Long id;

    @Column("PACIENTE_ID")
    private Long pacienteId;

    @Column("DOCTOR_ID")
    private Long doctorId;

    private LocalDate fecha;
    private String estado;
}