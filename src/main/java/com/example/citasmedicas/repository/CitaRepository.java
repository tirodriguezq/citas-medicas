package com.example.citasmedicas.repository;

import com.example.citasmedicas.model.Cita;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends ListCrudRepository<Cita, Long> {

    List<Cita> findByFecha(LocalDate fecha);

    List<Cita> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}