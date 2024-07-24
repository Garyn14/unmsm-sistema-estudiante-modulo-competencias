package com.UNMSM.moduloCompetencias.repository.ExtendidoCompetencia;

import com.UNMSM.moduloCompetencias.model.DTO.CompetenciaDTO;
import com.UNMSM.moduloCompetencias.model.DTO.CursoDTO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

public class CompetenciaRepositoryImpl implements CompetenciaRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<CompetenciaDTO> competenciasByPeriodoAlumno(Integer periodoId, Integer alumnoId) {
        MongoDatabase database = mongoTemplate.getDb();
        MongoCollection<Document> datawarehouseCollection = database.getCollection("datawarehouse");
        MongoCollection<Document> competenciasCollection = database.getCollection("competencias");

        List<Document> pipeline = new ArrayList<>();
        pipeline.add(new Document("$match", new Document("PeriodoAcademico_id", periodoId)
                .append("Alumno_id", alumnoId)));

        pipeline.add(new Document("$group", new Document("_id", "$Competencia_id")));

        List<CompetenciaDTO> result = new ArrayList<>();
        for(Document doc: datawarehouseCollection.aggregate(pipeline)){
            int competenciaId = doc.getInteger("_id");
            Document competenciaDoc = competenciasCollection.find(new Document("id", competenciaId)).first();
            if(competenciaDoc != null){
                CompetenciaDTO competenciaDTO = new CompetenciaDTO(
                        competenciaId,
                        competenciaDoc.getString("codigo"),
                        competenciaDoc.getString("nombre"),
                        competenciaDoc.getString("descripcion"),
                        competenciaDoc.getString("tipo")
                );
                result.add(competenciaDTO);
            }
        }

        return result;
    }
}
