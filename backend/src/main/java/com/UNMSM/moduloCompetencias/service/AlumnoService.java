package com.UNMSM.moduloCompetencias.service;

import com.UNMSM.moduloCompetencias.model.DTO.AlumnoDTO;
import com.UNMSM.moduloCompetencias.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<AlumnoDTO> getAll(){
        return alumnoRepository.findAllProyeccion();
    }

    public Optional<AlumnoDTO> getAlumnoById(Integer id){
        return alumnoRepository.findByIdProyeccion(id);
    }
}
