package com.UNMSM.moduloCompetencias.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataWarehousePromedio {
    private int id;
    private String competencia;
    private double promedioNota;
}

