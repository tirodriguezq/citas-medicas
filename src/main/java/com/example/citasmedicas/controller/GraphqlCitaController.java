package com.example.citasmedicas.controller;

import com.example.citasmedicas.model.Cita;
import com.example.citasmedicas.service.CitaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphqlCitaController {

    private final CitaService citaService;

    public GraphqlCitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @QueryMapping
    public List<Cita> citas() {
        return citaService.listarCitas();
    }

    @QueryMapping
    public Cita citaPorId(@Argument Long id) {
        return citaService.buscarPorId(id);
    }

    @MutationMapping
    public Cita cambiarEstadoCita(@Argument Long id, @Argument String estado) {
        return citaService.cambiarEstadoGraphQL(id, estado);
    }
}
