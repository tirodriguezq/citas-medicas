package com.example.citasmedicas.repository;

import com.example.citasmedicas.model.Doctor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends ListCrudRepository<Doctor, Long> {
}