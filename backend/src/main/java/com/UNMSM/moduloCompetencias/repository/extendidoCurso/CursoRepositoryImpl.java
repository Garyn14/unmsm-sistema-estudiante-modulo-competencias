package com.UNMSM.moduloCompetencias.repository.extendidoCurso;

import com.UNMSM.moduloCompetencias.model.DTO.CursoDTO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepositoryImpl implements CursoRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<CursoDTO> cursosByPeriodoAlumno(Integer periodoAcademicoId, Integer alumnoId) {
        MongoDatabase database = mongoTemplate.getDb();
        MongoCollection<Document> datawarehouseCollection = database.getCollection("datawarehouse");
        MongoCollection<Document> cursosCollection = database.getCollection("cursos");

        List<Document> pipeline = new ArrayList<>();
        pipeline.add(new Document("$match", new Document("PeriodoAcademico_id", periodoAcademicoId)
                .append("Alumno_id", alumnoId)));

        pipeline.add(new Document("$group", new Document("_id", "$Curso_id")));

        List<CursoDTO> result = new ArrayList<>();
        for(Document doc: datawarehouseCollection.aggregate(pipeline)){
            int cursoId = doc.getInteger("_id");
            Document cursoDoc = cursosCollection.find(new Document("id", cursoId)).first();
            if(cursoDoc != null){
                CursoDTO cursoDTO = new CursoDTO(
                        cursoId,
                        cursoDoc.getString("codigo"),
                        cursoDoc.getString("nombre"),
                        cursoDoc.getString("tipo")
                );
                result.add(cursoDTO);
            }
        }
        return result;
    }
}
