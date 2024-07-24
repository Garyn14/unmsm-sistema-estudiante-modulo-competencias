package com.UNMSM.moduloCompetencias.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CompetenciaPromedio {
    private Integer cursoId;
    private String nombre;
    private Double promedio;
}
