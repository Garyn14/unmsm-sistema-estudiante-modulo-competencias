package com.UNMSM.moduloCompetencias.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "competencias")
public class Competencia {
    @Id
    private String _id;
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String tipo;
}
