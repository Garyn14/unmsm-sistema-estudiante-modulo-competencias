package com.UNMSM.moduloCompetencias.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "datawarehouse")
public class DataWarehouse {
    @Id
    private String _id;
    private Integer PeriodoAcademico_id;
    private Integer Alumno_id;
    private Integer Curso_id;
    private Integer CursoComponente_id;
    private Integer Competencia_id;
    private double AlumnoNotas_nota;
}
