package com.UNMSM.moduloCompetencias.repository;

import com.UNMSM.moduloCompetencias.model.Competencia;
import com.UNMSM.moduloCompetencias.model.DTO.CompetenciaDTO;
import com.UNMSM.moduloCompetencias.repository.ExtendidoCompetencia.CompetenciaRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompetenciaRepository extends MongoRepository<Competencia, String>, CompetenciaRepositoryCustom {

    @Query(value = "{}", fields = "{'_id': 0, 'id': 1, 'codigo': 1, 'nombre': 1, 'descripcion': 1, 'tipo': 1}")
    List<CompetenciaDTO> findAllProyeccion();

    @Query(value = "{ 'id': ?0 }", fields = "{'_id': 0, 'id': 1, 'codigo': 1, 'nombre': 1, 'descripcion': 1, 'tipo': 1}")
    Optional<CompetenciaDTO> findByIdProyeccion(Integer id);
}
