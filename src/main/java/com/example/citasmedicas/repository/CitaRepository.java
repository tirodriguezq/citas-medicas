package com.example.citasmedicas.repository;

import com.example.citasmedicas.model.Cita;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CitaRepository {

    private final List<Cita> citas = new ArrayList<>();
    private Long contadorId = 1L;

    public void guardar(Cita cita) {
        cita.setId(contadorId++);
        citas.add(cita);
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
        Cita cita = buscarPorId(id);
        if (cita != null) {
            cita.setEstado(nuevoEstado);
        }
    }

    public List<Cita> obtenerPorFecha(LocalDate fecha) {
        List<Cita> resultado = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getFecha().equals(fecha)) {
                resultado.add(cita);
            }
        }

        return resultado;
    }

    public List<Cita> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Cita> resultado = new ArrayList<>();

        for (Cita cita : citas) {
            if ((cita.getFecha().isEqual(fechaInicio) || cita.getFecha().isAfter(fechaInicio))
                    && (cita.getFecha().isEqual(fechaFin) || cita.getFecha().isBefore(fechaFin))) {
                resultado.add(cita);
            }
        }

        return resultado;
    }
}