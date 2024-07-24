package com.UNMSM.moduloCompetencias.service;

import com.UNMSM.moduloCompetencias.model.CompetenciaPromedio;
import com.UNMSM.moduloCompetencias.model.DTO.AvanceVsPromedioDTO;
import com.UNMSM.moduloCompetencias.model.DataWarehousePromedio;
import com.UNMSM.moduloCompetencias.repository.CursoRepository;
import com.UNMSM.moduloCompetencias.repository.DataWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataWarehouseService {

    @Autowired
    private DataWarehouseRepository dataWarehouseRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<DataWarehousePromedio> getPromNotasByCompetenciaId(int periodoAcademicoId, int alumnoId, int cursoId) {
        return dataWarehouseRepository
                .promNotasByCompetenciaId(periodoAcademicoId, alumnoId, cursoId)
                .stream()
                .sorted(Comparator.comparing(DataWarehousePromedio::getCompetencia))
                .collect(Collectors.toList());
    }

    public List<CompetenciaPromedio> obtenerPromedioPorCompetencia(Integer periodoAcademico_id, Integer alumno_id, Integer competencia_id) {
        return dataWarehouseRepository
                .promNotasByCursoCompetencia(periodoAcademico_id, alumno_id, competencia_id)
                .stream()
                .sorted(Comparator.comparing(CompetenciaPromedio::getNombre))
                .collect(Collectors.toList());
    }

    public AvanceVsPromedioDTO getProgesoVsSalon(int periodoId, int alumnoId, int cursoId, int competenciaId){
        return dataWarehouseRepository.avanceVsPromedio(periodoId, alumnoId, cursoId, competenciaId);
    }
}
