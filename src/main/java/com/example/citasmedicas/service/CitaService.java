package com.example.citasmedicas.service;

import com.example.citasmedicas.model.Cita;
import com.example.citasmedicas.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public void crearCita(Cita cita) {
        validarCita(cita);
        citaRepository.guardar(cita);
    }

    public List<Cita> listarCitas() {
        return citaRepository.obtenerTodas();
    }

    public void cambiarEstado(Long id) {
        Cita cita = citaRepository.buscarPorId(id);

        if (cita != null && cita.getEstado().equals("Programada")) {
            citaRepository.actualizarEstado(id, "Atendida");
        }
    }

    private void validarCita(Cita cita) {
        if (cita.getNombrePaciente() == null || cita.getNombrePaciente().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente es obligatorio.");
        }

        if (cita.getDoctor() == null || cita.getDoctor().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del doctor es obligatorio.");
        }

        if (cita.getFecha() == null || cita.getFecha().trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de la cita es obligatoria.");
        }

        if (cita.getEstado() == null || cita.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado de la cita es obligatorio.");
        }
    }
}