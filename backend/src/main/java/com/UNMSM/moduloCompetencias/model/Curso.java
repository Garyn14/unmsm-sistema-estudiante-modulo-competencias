package com.UNMSM.moduloCompetencias.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cursos")
public class Curso {
    @Id
    private String _id;
    private Integer id;
    private String codigo;
    private String nombre;
    private String tipo;
    private String ciclo;
    private Integer periodoacademicoid;
    private Integer planestudiosid;
    private String planestudios_ref;
    private String periodo_ref;
}
