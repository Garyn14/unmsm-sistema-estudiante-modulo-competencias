package com.UNMSM.moduloCompetencias.service;

import com.UNMSM.moduloCompetencias.model.DTO.CompetenciaDTO;
import com.UNMSM.moduloCompetencias.repository.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    public List<CompetenciaDTO> getAllCompetencias(){
        return competenciaRepository.findAllProyeccion();
    }

    public CompetenciaDTO getCompetenciaById(Integer id){
        return competenciaRepository.findByIdProyeccion(id).orElse(null);
    }

    public List<CompetenciaDTO> getCompetenciasByPeriodoAlumno(Integer periodoId, Integer alumnoId){
        return competenciaRepository
                .competenciasByPeriodoAlumno(periodoId, alumnoId)
                .stream()
                .sorted(Comparator.comparing(CompetenciaDTO::getNombre))
                .collect(Collectors.toList());
    }
}
