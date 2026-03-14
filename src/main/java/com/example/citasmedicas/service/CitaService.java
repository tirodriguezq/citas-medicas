package com.example.citasmedicas.service;

import com.example.citasmedicas.model.Cita;
import com.example.citasmedicas.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public void crearCita(Cita cita) {
        validarCita(cita);
        cita.setEstado("Programada");
        citaRepository.guardar(cita);
    }

    public List<Cita> listarCitas() {
        return citaRepository.obtenerTodas();
    }

    public void cambiarEstado(Long id) {
        Cita cita = citaRepository.buscarPorId(id);

        if (cita != null && "Programada".equals(cita.getEstado())) {
            citaRepository.actualizarEstado(id, "Atendida");
        }
    }

    public void cancelarCita(Long id) {
        Cita cita = citaRepository.buscarPorId(id);

        if (cita != null && "Programada".equals(cita.getEstado())) {
            citaRepository.actualizarEstado(id, "Cancelada");
        }
    }

    public List<Cita> agendaDelDia(LocalDate fecha) {
        return citaRepository.obtenerPorFecha(fecha);
    }

    private void validarCita(Cita cita) {
        if (cita.getNombrePaciente() == null || cita.getNombrePaciente().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente es obligatorio.");
        }

        if (cita.getDoctor() == null || cita.getDoctor().trim().isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar un doctor.");
        }

        if (cita.getFecha() == null) {
            throw new IllegalArgumentException("La fecha de la cita es obligatoria.");
        }

        if (cita.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se permiten fechas anteriores al día actual.");
        }

        for (Cita existente : citaRepository.obtenerTodas()) {
            if (existente.getDoctor().equals(cita.getDoctor())
                    && existente.getFecha().equals(cita.getFecha())
                    && "Programada".equals(existente.getEstado())) {
                throw new IllegalArgumentException("El doctor ya tiene una cita programada en esa fecha.");
            }
        }
    }
}