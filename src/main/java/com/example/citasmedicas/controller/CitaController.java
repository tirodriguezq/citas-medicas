package com.example.citasmedicas.controller;

import com.example.citasmedicas.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}