package com.productionmanagement.repository;

import com.productionmanagement.domain.entities.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NomenclatureRepository extends JpaRepository<Nomenclature, String> {

    @Query("SELECT n.tableName FROM Nomenclature AS n WHERE n.id = 1")
    String getNomenclatureTableName(int nomId);
}
