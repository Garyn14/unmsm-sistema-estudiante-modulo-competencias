package com.UNMSM.moduloCompetencias.repository;

import com.UNMSM.moduloCompetencias.model.Curso;
import com.UNMSM.moduloCompetencias.model.DTO.CursoDTO;
import com.UNMSM.moduloCompetencias.repository.extendidoCurso.CursoRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends MongoRepository<Curso, String>, CursoRepositoryCustom {
    @Query(value = "{}", fields = "{'_id': 0, 'id': 1, 'codigo': 1, 'nombre': 1, 'tipo': 1}")
    List<CursoDTO> findAllProyeccion();

    @Query(value = "{ 'id': ?0 }", fields = "{'_id': 0, 'id': 1, 'codigo': 1, 'nombre': 1, 'tipo': 1}")
    Optional<CursoDTO> findByIdProyeccion(Integer id);
}
