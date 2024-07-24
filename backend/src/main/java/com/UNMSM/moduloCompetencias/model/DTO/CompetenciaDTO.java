package com.UNMSM.moduloCompetencias.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id", "codigo", "nombre"})
@AllArgsConstructor
public class CompetenciaDTO {
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String tipo;
}
