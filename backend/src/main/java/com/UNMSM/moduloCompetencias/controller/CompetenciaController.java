package com.UNMSM.moduloCompetencias.controller;

import com.UNMSM.moduloCompetencias.model.DTO.CompetenciaDTO;
import com.UNMSM.moduloCompetencias.service.CompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/competencias")
public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;

    @GetMapping
    public List<CompetenciaDTO> getAllCompetencias(){
        return competenciaService.getAllCompetencias();
    }

    @GetMapping("/{id}")
    public CompetenciaDTO getCompetenciaById(@PathVariable Integer id){
        return competenciaService.getCompetenciaById(id);
    }

    @GetMapping("/competenciasByPeriodoAlumno")
    List<CompetenciaDTO> getCompetenciasByPeriodoIdAlumnoId(
            @RequestParam Integer periodoId,
            @RequestParam Integer alumnoId){
        return competenciaService.getCompetenciasByPeriodoAlumno(periodoId, alumnoId);
    }
}
