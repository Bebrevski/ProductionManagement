package com.productionmanagement.service;

import com.productionmanagement.domain.models.production.ProductionServiceModel;
import com.productionmanagement.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionServiceImpl implements ProductionService{

    private final ProductionRepository productionRepository;

    @Autowired
    public ProductionServiceImpl(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Override
    public ProductionServiceModel createProduction(ProductionServiceModel model) {
        return null;
    }
}
