package com.UNMSM.moduloCompetencias.repository.extendidoCurso;

import com.UNMSM.moduloCompetencias.model.DTO.CursoDTO;

import java.util.List;

public interface CursoRepositoryCustom {
    List<CursoDTO> cursosByPeriodoAlumno(Integer periodoId, Integer alumnoId);
}
