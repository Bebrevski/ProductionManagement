package com.productionmanagement.repository;

import com.productionmanagement.domain.entities.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, String> {
    List<UnitOfMeasure> findAllByActive(boolean active);
}
