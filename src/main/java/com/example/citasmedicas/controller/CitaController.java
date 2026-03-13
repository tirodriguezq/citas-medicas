package com.example.citasmedicas.controller;

import com.example.citasmedicas.model.Cita;
import com.example.citasmedicas.service.CitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public List<Cita> listarCitas() {
        return citaService.listarCitas();
    }

    @PostMapping
    public void crearCita(@RequestBody Cita cita) {
        citaService.crearCita(cita);
    }
}
