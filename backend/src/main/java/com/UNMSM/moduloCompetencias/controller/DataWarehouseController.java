package com.UNMSM.moduloCompetencias.controller;

import com.UNMSM.moduloCompetencias.model.CompetenciaPromedio;
import com.UNMSM.moduloCompetencias.model.DTO.AvanceVsPromedioDTO;
import com.UNMSM.moduloCompetencias.model.DataWarehousePromedio;
import com.UNMSM.moduloCompetencias.service.DataWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/datawarehouse")
public class DataWarehouseController {

    @Autowired
    private DataWarehouseService dataWarehouseService;

    @GetMapping("/competencia-promedio")
    public List<DataWarehousePromedio> getCompetenciaProm(
            @RequestParam int periodoAcademicoId,
            @RequestParam int alumnoId,
            @RequestParam int cursoId) {
        return dataWarehouseService.getPromNotasByCompetenciaId(periodoAcademicoId, alumnoId, cursoId);
    }

    @GetMapping("/promedio")
    public List<CompetenciaPromedio> obtenerPromedioPorCompetencia(
            @RequestParam Integer periodoAcademicoId,
            @RequestParam Integer alumnoId,
            @RequestParam Integer competenciaId) {
        return dataWarehouseService.obtenerPromedioPorCompetencia(periodoAcademicoId, alumnoId, competenciaId);
    }

    @GetMapping("/progreso-vs-salon")
    public ResponseEntity<AvanceVsPromedioDTO> getProgresoVsSalon(
            @RequestParam Integer periodoId,
            @RequestParam Integer alumnoId,
            @RequestParam Integer cursoId,
            @RequestParam Integer competenciaId
    ){
        return ResponseEntity
                .ok(dataWarehouseService
                        .getProgesoVsSalon(periodoId, alumnoId, cursoId, competenciaId)
                );
    }
}



