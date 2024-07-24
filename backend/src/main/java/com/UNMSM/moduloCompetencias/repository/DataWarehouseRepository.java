package com.UNMSM.moduloCompetencias.repository;

import com.UNMSM.moduloCompetencias.model.DataWarehouse;
import com.UNMSM.moduloCompetencias.repository.extendidoDWH.DataWarehouseRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DataWarehouseRepository extends MongoRepository<DataWarehouse, String>, DataWarehouseRepositoryCustom {
}

