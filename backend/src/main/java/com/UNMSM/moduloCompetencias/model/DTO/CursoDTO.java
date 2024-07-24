package com.UNMSM.moduloCompetencias.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CursoDTO {
    private Integer id;
    private String codigo;
    private String nombre;
    private String tipo;
}
