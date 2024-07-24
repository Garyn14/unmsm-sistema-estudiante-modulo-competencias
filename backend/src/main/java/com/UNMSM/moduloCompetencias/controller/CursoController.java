package com.UNMSM.moduloCompetencias.controller;

import com.UNMSM.moduloCompetencias.model.DTO.CursoDTO;
import com.UNMSM.moduloCompetencias.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<CursoDTO> getAllCursos(){
        return cursoService.getAllCursos();
    }

    @GetMapping("/{id}")
    public CursoDTO getCursoById(@PathVariable Integer id){
        return cursoService.getCursoById(id);
    }

    @GetMapping("/cursosByPeriodoAlumno")
    public List<CursoDTO> getCursosByPeriodoIdAlumnoId(
            @RequestParam Integer periodoId,
            @RequestParam Integer alumnoId){
        return cursoService.getCursosByPeriodoAlumno(periodoId, alumnoId);
    }
}
