package com.example.citasmedicas.repository;

import com.example.citasmedicas.model.Cita;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CitaRepository {

    private List<Cita> citas = new ArrayList<>();
    private Long contadorId = 1L;

    public void guardar(Cita cita) {
        Cita nuevaCita = new Cita(
                contadorId++,
                cita.getNombrePaciente(),
                cita.getDoctor(),
                cita.getFecha(),
                cita.getEstado()
        );
        citas.add(nuevaCita);
    }

    public List<Cita> obtenerTodas() {
        return citas;
    }

    public Cita buscarPorId(Long id) {
        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                return cita;
            }
        }
        return null;
    }

    public void actualizarEstado(Long id, String nuevoEstado) {
        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                cita.setEstado(nuevoEstado);
                break;
            }
        }
    }
}