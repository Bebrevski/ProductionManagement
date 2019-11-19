package com.productionmanagement.repository;

import com.productionmanagement.domain.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
    List<Stock> findAllByProduction_UuidAndActiveIsTrue(String uuid);

    Optional<Stock> findByUuidAndActiveIsTrue(String uuid);

    Optional<Stock> findById(int id);
}
