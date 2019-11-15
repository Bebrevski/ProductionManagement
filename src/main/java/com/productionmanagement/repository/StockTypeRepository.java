package com.productionmanagement.repository;

import com.productionmanagement.domain.entities.StockType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockTypeRepository extends JpaRepository<StockType, String> {
    List<StockType> findAllByActive(boolean active);
}
