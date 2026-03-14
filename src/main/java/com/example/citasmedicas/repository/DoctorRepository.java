package com.example.citasmedicas.repository;

import com.example.citasmedicas.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorRepository {

    private final List<Doctor> doctores = new ArrayList<>();

    public DoctorRepository() {
        doctores.add(new Doctor(1L, "Dr. Ramírez", "Medicina General"));
        doctores.add(new Doctor(2L, "Dra. López", "Pediatría"));
        doctores.add(new Doctor(3L, "Dr. Fernández", "Cardiología"));
    }

    public List<Doctor> obtenerTodos() {
        return doctores;
    }
}