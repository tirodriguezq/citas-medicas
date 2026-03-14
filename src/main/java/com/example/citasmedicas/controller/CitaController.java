package com.example.citasmedicas.controller;

import com.example.citasmedicas.model.Cita;
import com.example.citasmedicas.service.CitaService;
import com.example.citasmedicas.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CitaController {

    private final CitaService citaService;
    private final DoctorService doctorService;

    public CitaController(CitaService citaService, DoctorService doctorService) {
        this.citaService = citaService;
        this.doctorService = doctorService;
    }

    @GetMapping("/citas")
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.listarCitas());
        return "citas";
    }

    @GetMapping("/nueva-cita")
    public String mostrarFormularioNuevaCita(Model model) {
        model.addAttribute("doctores", doctorService.listarDoctores());
        return "nueva-cita";
    }

    @PostMapping("/guardar-cita")
    public String guardarCita(@ModelAttribute Cita cita, Model model) {
        try {
            citaService.crearCita(cita);
            return "redirect:/citas";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("doctores", doctorService.listarDoctores());
            return "nueva-cita";
        }
    }

    @GetMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        citaService.cambiarEstado(id);
        return "redirect:/citas";
    }
    @GetMapping("/cancelar-cita/{id}")
    public String cancelarCita(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return "redirect:/citas";
    }
}