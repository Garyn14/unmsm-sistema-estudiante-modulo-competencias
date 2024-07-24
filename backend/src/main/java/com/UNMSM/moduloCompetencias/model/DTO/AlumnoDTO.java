package com.UNMSM.moduloCompetencias.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class AlumnoDTO {
    private Integer id;
    private Integer codigo;
    private String nombres;
    private String apellidos;
    private String email;
}
