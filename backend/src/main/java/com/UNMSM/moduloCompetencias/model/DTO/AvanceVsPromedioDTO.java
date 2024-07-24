package com.UNMSM.moduloCompetencias.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AvanceVsPromedioDTO {
    private String curso;
    private String competencia;
    private Double miProgreso;
    private Double progresoSalon;
}
