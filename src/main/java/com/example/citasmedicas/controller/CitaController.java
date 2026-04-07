package com.example.citasmedicas.controller;

import com.example.citasmedicas.model.Cita;
import com.example.citasmedicas.service.CitaService;
import com.example.citasmedicas.service.DoctorService;
import com.example.citasmedicas.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class CitaController {

    private final CitaService citaService;
    private final DoctorService doctorService;
    private final PacienteService pacienteService;

    public CitaController(CitaService citaService, DoctorService doctorService, PacienteService pacienteService) {
        this.citaService = citaService;
        this.doctorService = doctorService;
        this.pacienteService = pacienteService;
    }

    @GetMapping("/citas")
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.listarCitas());
        return "citas";
    }

    @GetMapping("/nueva-cita")
    public String mostrarFormularioNuevaCita(Model model) {
        model.addAttribute("doctores", doctorService.listarDoctores());
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("cita", new Cita());
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
            model.addAttribute("pacientes", pacienteService.listarPacientes());
            model.addAttribute("cita", cita);
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

    @GetMapping("/agenda")
    public String verAgenda(@RequestParam(required = false) String fecha, Model model) {
        LocalDate fechaConsulta;

        if (fecha == null || fecha.isBlank()) {
            fechaConsulta = LocalDate.now();
        } else {
            fechaConsulta = LocalDate.parse(fecha);
        }

        model.addAttribute("fechaSeleccionada", fechaConsulta);
        model.addAttribute("citas", citaService.agendaDelDia(fechaConsulta));
        return "agenda";
    }

    @GetMapping("/agenda-semanal")
    public String verAgendaSemanal(@RequestParam(required = false) String fechaInicio, Model model) {
        LocalDate inicioSemana;

        if (fechaInicio == null || fechaInicio.isBlank()) {
            inicioSemana = LocalDate.now();
        } else {
            inicioSemana = LocalDate.parse(fechaInicio);
        }

        LocalDate finSemana = inicioSemana.plusDays(6);

        model.addAttribute("fechaInicio", inicioSemana);
        model.addAttribute("fechaFin", finSemana);
        model.addAttribute("citas", citaService.agendaDeLaSemana(inicioSemana));

        return "agenda-semanal";
    }
}