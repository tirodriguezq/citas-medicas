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

    public Cita crearCita(Cita cita) {
        validarCita(cita);
        cita.setEstado("PROGRAMADA");
        return citaRepository.save(cita);
    }

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public Cita buscarPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    public void cambiarEstado(Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);

        if (cita != null && "PROGRAMADA".equals(cita.getEstado())) {
            cita.setEstado("ATENDIDA");
            citaRepository.save(cita);
        }
    }

    public void cancelarCita(Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);

        if (cita != null && "PROGRAMADA".equals(cita.getEstado())) {
            cita.setEstado("CANCELADA");
            citaRepository.save(cita);
        }
    }

    public Cita cambiarEstadoGraphQL(Long id, String nuevoEstado) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));

        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }

        cita.setEstado(nuevoEstado.toUpperCase());
        return citaRepository.save(cita);
    }

    public List<Cita> agendaDelDia(LocalDate fecha) {
        return citaRepository.findByFecha(fecha);
    }

    public List<Cita> agendaDeLaSemana(LocalDate fechaInicio) {
        LocalDate fechaFin = fechaInicio.plusDays(6);
        return citaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

    private void validarCita(Cita cita) {
        if (cita.getPacienteId() == null) {
            throw new IllegalArgumentException("Debe seleccionar un paciente.");
        }

        if (cita.getDoctorId() == null) {
            throw new IllegalArgumentException("Debe seleccionar un doctor.");
        }

        if (cita.getFecha() == null) {
            throw new IllegalArgumentException("La fecha de la cita es obligatoria.");
        }

        if (cita.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se permiten fechas anteriores al día actual.");
        }

        for (Cita existente : citaRepository.findAll()) {
            if (existente.getDoctorId().equals(cita.getDoctorId())
                    && existente.getFecha().equals(cita.getFecha())
                    && "PROGRAMADA".equals(existente.getEstado())) {
                throw new IllegalArgumentException("El doctor ya tiene una cita programada en esa fecha.");
            }
        }
    }
}