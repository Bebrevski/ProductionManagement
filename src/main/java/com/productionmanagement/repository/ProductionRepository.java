package com.productionmanagement.repository;

import com.productionmanagement.domain.entities.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductionRepository extends JpaRepository<Production, String> {
    Optional<Production> getByUuid(String uuid);
}
