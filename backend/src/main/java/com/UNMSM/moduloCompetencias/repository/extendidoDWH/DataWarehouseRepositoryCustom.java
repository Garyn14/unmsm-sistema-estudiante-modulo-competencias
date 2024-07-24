package com.UNMSM.moduloCompetencias.repository.extendidoDWH;

import com.UNMSM.moduloCompetencias.model.CompetenciaPromedio;
import com.UNMSM.moduloCompetencias.model.DTO.AvanceVsPromedioDTO;
import com.UNMSM.moduloCompetencias.model.DataWarehousePromedio;

import java.util.List;

public interface DataWarehouseRepositoryCustom {
    List<DataWarehousePromedio> promNotasByCompetenciaId(int periodoAcademicoId, int alumnoId, int cursoId);
    List<CompetenciaPromedio> promNotasByCursoCompetencia(int periodoAcademicoId, int alumnoId, int competenciaId);

    AvanceVsPromedioDTO avanceVsPromedio(int periodoAcademicoId, int alumnoId, int cursoId, int competenciaId);
}


