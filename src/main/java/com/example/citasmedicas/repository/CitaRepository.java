package com.example.citasmedicas.repository;

import com.example.citasmedicas.model.Cita;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CitaRepository {

    private final List<Cita> citas = new ArrayList<>();

    public List<Cita> listarCitas() {
        return citas;
    }

    public void guardarCita(Cita cita) {
        citas.add(cita);
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
        Cita cita = buscarPorId(id);
        if (cita != null) {
            cita.setEstado(nuevoEstado);
        }
    }
}