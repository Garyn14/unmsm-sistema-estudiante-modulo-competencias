package com.UNMSM.moduloCompetencias.repository;

import com.UNMSM.moduloCompetencias.model.Alumno;
import com.UNMSM.moduloCompetencias.model.DTO.AlumnoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlumnoRepository extends MongoRepository<Alumno, String> {
    @Query(value = "{}", fields = "{'_id': 0}")
    List<AlumnoDTO> findAllProyeccion();

    @Query(value = "{ 'id': ?0 }", fields = "{'_id': 0}")
    Optional<AlumnoDTO> findByIdProyeccion(Integer id);
}
