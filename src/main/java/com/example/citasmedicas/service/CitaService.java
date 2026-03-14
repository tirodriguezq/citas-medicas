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

    public List<Cita> listarCitas() {
        return citaRepository.listarCitas();
    }

    public void crearCita(Cita cita) {

        if (cita.getNombrePaciente() == null || cita.getNombrePaciente().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente es obligatorio");
        }

        if (cita.getDoctor() == null || cita.getDoctor().isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar un doctor");
        }

        if (cita.getFecha() == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }

        if (cita.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden registrar citas en fechas pasadas");
        }

        for (Cita c : citaRepository.listarCitas()) {
            if (c.getDoctor().equals(cita.getDoctor()) &&
                    c.getFecha().equals(cita.getFecha()) &&
                    c.getEstado().equals("Programada")) {

                throw new IllegalArgumentException("El doctor ya tiene una cita programada ese día");
            }
        }

        cita.setEstado("Programada");
        citaRepository.guardarCita(cita);
    }

    public void cambiarEstado(Long id) {
        citaRepository.actualizarEstado(id, "Atendida");
    }

    public void cancelarCita(Long id) {
        Cita cita = citaRepository.buscarPorId(id);

        if (cita != null && cita.getEstado().equals("Programada")) {
            citaRepository.actualizarEstado(id, "Cancelada");
        }
    }
}