package com.UNMSM.moduloCompetencias.controller;

import com.UNMSM.moduloCompetencias.model.DTO.AlumnoDTO;
import com.UNMSM.moduloCompetencias.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<AlumnoDTO> getAllAlumnos(){
        return alumnoService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<AlumnoDTO> getAlumnoById(@PathVariable Integer id){
        return alumnoService.getAlumnoById(id);
    }
}
