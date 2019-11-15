package com.productionmanagement.repository;

import com.productionmanagement.domain.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
    List<Stock> findAllByProduction_Uuid(String uuid);
}
