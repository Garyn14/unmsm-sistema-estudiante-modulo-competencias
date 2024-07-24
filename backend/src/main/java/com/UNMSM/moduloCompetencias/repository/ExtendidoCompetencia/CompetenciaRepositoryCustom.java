package com.UNMSM.moduloCompetencias.repository.ExtendidoCompetencia;

import com.UNMSM.moduloCompetencias.model.DTO.CompetenciaDTO;

import java.util.List;

public interface CompetenciaRepositoryCustom {
    List<CompetenciaDTO> competenciasByPeriodoAlumno(Integer periodoId, Integer alumnoId);
}
