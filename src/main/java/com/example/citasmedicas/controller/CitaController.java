package com.example.citasmedicas.controller;

import com.example.citasmedicas.model.Cita;
import com.example.citasmedicas.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping("/citas")
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.listarCitas());
        return "citas";
    }

    @GetMapping("/nueva-cita")
    public String mostrarFormularioNuevaCita() {
        return "nueva-cita";
    }

    @PostMapping("/guardar-cita")
    public String guardarCita(@ModelAttribute Cita cita, Model model) {
        try {
            citaService.crearCita(cita);
            return "redirect:/citas";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "nueva-cita";
        }
    }

    @GetMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        citaService.cambiarEstado(id);
        return "redirect:/citas";
    }
}