package com.UNMSM.moduloCompetencias.repository.extendidoDWH;

import com.UNMSM.moduloCompetencias.model.CompetenciaPromedio;
import com.UNMSM.moduloCompetencias.model.DTO.AvanceVsPromedioDTO;
import com.UNMSM.moduloCompetencias.model.DataWarehousePromedio;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataWarehouseRepositoryImpl implements DataWarehouseRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<DataWarehousePromedio> promNotasByCompetenciaId(int periodoAcademicoId, int alumnoId, int cursoId) {
        MongoDatabase database = mongoTemplate.getDb();
        MongoCollection<Document> datawarehouseCollection = database.getCollection("datawarehouse");
        MongoCollection<Document> competenciasCollection = database.getCollection("competencias");

        List<Document> pipeline = new ArrayList<>();
        pipeline.add(new Document("$match", new Document("PeriodoAcademico_id", periodoAcademicoId)
                .append("Alumno_id", alumnoId)
                .append("Curso_id", cursoId)));
        pipeline.add(new Document("$group", new Document("_id", "$Competencia_id")
                .append("promedioNota", new Document("$avg", "$AlumnoNotas_nota"))));

        List<DataWarehousePromedio> result = new ArrayList<>();
        for (Document doc : datawarehouseCollection.aggregate(pipeline)) {
            int competenciaId = doc.getInteger("_id");
            Document competenciaDoc = competenciasCollection.find(new Document("id", competenciaId)).first();
            String competenciaNombre = competenciaDoc != null ? competenciaDoc.getString("nombre") : "Desconocido";
            double promedioNota = doc.getDouble("promedioNota");
            BigDecimal promedioNotaRounded = new BigDecimal(promedioNota).setScale(1, RoundingMode.HALF_UP);
            DataWarehousePromedio dataWarehousePromedio = new DataWarehousePromedio(competenciaId, competenciaNombre, promedioNotaRounded.doubleValue());
            result.add(dataWarehousePromedio);
        }
        return result;
    }

    @Override
    public List<CompetenciaPromedio> promNotasByCursoCompetencia(int periodoAcademicoId, int alumnoId, int competenciaId) {
        MongoDatabase database = mongoTemplate.getDb();
        MongoCollection<Document> datawarehouseCollection = database.getCollection("datawarehouse");
        MongoCollection<Document> cursosCollection = database.getCollection("cursos");

        List<Document> pipeline = new ArrayList<>();
        pipeline.add(new Document("$match", new Document("PeriodoAcademico_id", periodoAcademicoId)
                .append("Alumno_id", alumnoId)
                .append("Competencia_id", competenciaId)));
        pipeline.add(new Document("$group", new Document("_id", "$Curso_id")
                .append("promedioNota", new Document("$avg", "$AlumnoNotas_nota"))));

        List<CompetenciaPromedio> result = new ArrayList<>();
        for (Document doc : datawarehouseCollection.aggregate(pipeline)) {
            int cursoId = doc.getInteger("_id");
            Document cursoDoc = cursosCollection.find(new Document("id", cursoId)).first();
            String cursoNombre = cursoDoc != null ? cursoDoc.getString("nombre") : "Desconocido";
            double promedioNota = doc.getDouble("promedioNota");
            BigDecimal promedioNotaRounded = new BigDecimal(promedioNota).setScale(1, RoundingMode.HALF_UP);
            CompetenciaPromedio competenciaPromedio = new CompetenciaPromedio(cursoId, cursoNombre, promedioNotaRounded.doubleValue());
            result.add(competenciaPromedio);
        }
        return result;
    }

    @Override
    public AvanceVsPromedioDTO avanceVsPromedio(int periodoAcademicoId, int alumnoId, int cursoId, int competenciaId) {
        MongoDatabase database = mongoTemplate.getDb();
        MongoCollection<Document> datawarehouseCollection = database.getCollection("datawarehouse");
        MongoCollection<Document> cursosCollection = database.getCollection("cursos");
        MongoCollection<Document> competenciasCollection = database.getCollection("competencias");

        // Pipeline para obtener el progreso del alumno
        List<Document> pipelineAlumno = new ArrayList<>();
        pipelineAlumno.add(new Document("$match", new Document("PeriodoAcademico_id", periodoAcademicoId)
                .append("Alumno_id", alumnoId)
                .append("Curso_id", cursoId)
                .append("Competencia_id", competenciaId)));
        pipelineAlumno.add(new Document("$group", new Document("_id", new Document("PeriodoAcademico_id", "$PeriodoAcademico_id")
                .append("Alumno_id", "$Alumno_id")
                .append("Curso_id", "$Curso_id")
                .append("Competencia_id", "$Competencia_id"))
                .append("TotalNotas", new Document("$avg", "$AlumnoNotas_nota"))));

        Document resultadoAlumno = datawarehouseCollection.aggregate(pipelineAlumno).first();

        // Pipeline para obtener el progreso del salón
        List<Document> pipelineSalon = new ArrayList<>();
        pipelineSalon.add(new Document("$match", new Document("PeriodoAcademico_id", periodoAcademicoId)
                .append("Curso_id", cursoId)
                .append("Competencia_id", competenciaId)));
        pipelineSalon.add(new Document("$group", new Document("_id", new Document("PeriodoAcademico_id", "$PeriodoAcademico_id")
                .append("Curso_id", "$Curso_id")
                .append("Competencia_id", "$Competencia_id"))
                .append("PromedioNotas", new Document("$avg", "$AlumnoNotas_nota"))));

        Document resultadoSalon = datawarehouseCollection.aggregate(pipelineSalon).first();

        if (resultadoAlumno != null && resultadoSalon != null) {
            String cursoNombre = getNombreCurso(cursosCollection, cursoId);
            String competenciaNombre = getNombreCompetencia(competenciasCollection, competenciaId);

            double miProgreso = new BigDecimal(resultadoAlumno.getDouble("TotalNotas")).setScale(2, RoundingMode.HALF_UP).doubleValue();
            double progresoSalon = new BigDecimal(resultadoSalon.getDouble("PromedioNotas")).setScale(2, RoundingMode.HALF_UP).doubleValue();

            return new AvanceVsPromedioDTO(cursoNombre, competenciaNombre, miProgreso, progresoSalon);
        }

        return null;
    }

    private String getNombreCurso(MongoCollection<Document> cursosCollection, int cursoId) {
        Document cursoDoc = cursosCollection.find(new Document("id", cursoId)).first();
        return cursoDoc != null ? cursoDoc.getString("nombre") : "Desconocido";
    }

    private String getNombreCompetencia(MongoCollection<Document> competenciasCollection, int competenciaId) {
        Document competenciaDoc = competenciasCollection.find(new Document("id", competenciaId)).first();
        return competenciaDoc != null ? competenciaDoc.getString("nombre") : "Desconocido";
    }
}


