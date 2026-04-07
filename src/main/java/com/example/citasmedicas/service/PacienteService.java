package com.example.citasmedicas.service;

import com.example.citasmedicas.model.Paciente;
import com.example.citasmedicas.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}