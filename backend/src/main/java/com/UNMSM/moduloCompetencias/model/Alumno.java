package com.UNMSM.moduloCompetencias.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "alumnos")
public class Alumno {
    @Id
    private String _id;
    private Integer id;
    private Integer codigo;
    private String nombres;
    private String apellidos;
    private String email;
}
