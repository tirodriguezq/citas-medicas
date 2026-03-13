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
}