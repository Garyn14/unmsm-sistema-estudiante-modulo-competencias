package com.UNMSM.moduloCompetencias.service;

import com.UNMSM.moduloCompetencias.model.DTO.CursoDTO;
import com.UNMSM.moduloCompetencias.repository.CursoRepository;
import com.UNMSM.moduloCompetencias.repository.DataWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DataWarehouseRepository dataWarehouseRepository;

    public List<CursoDTO> getAllCursos(){
        return cursoRepository.findAllProyeccion();
    }

    public CursoDTO getCursoById(Integer id){
        return cursoRepository.findByIdProyeccion(id).orElse(null);
    }

    public List<CursoDTO> getCursosByPeriodoAlumno(Integer periodoId, Integer alumnoId){
        return cursoRepository
                .cursosByPeriodoAlumno(periodoId, alumnoId)
                .stream()
                .sorted(Comparator.comparing(CursoDTO::getNombre))
                .collect(Collectors.toList());
    }
}
