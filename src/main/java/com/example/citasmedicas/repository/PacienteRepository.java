package com.example.citasmedicas.repository;

import com.example.citasmedicas.model.Paciente;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends ListCrudRepository<Paciente, Long> {
}