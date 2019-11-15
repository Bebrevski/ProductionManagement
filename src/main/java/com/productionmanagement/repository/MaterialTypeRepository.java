package com.productionmanagement.repository;

import com.productionmanagement.domain.entities.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialTypeRepository extends JpaRepository<MaterialType, String> {
    List<MaterialType> findAllByActive(boolean active);
}
