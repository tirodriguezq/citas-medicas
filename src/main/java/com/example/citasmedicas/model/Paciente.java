package com.example.citasmedicas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("PACIENTES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    private Long id;
    private String nombre;
    private String telefono;
}