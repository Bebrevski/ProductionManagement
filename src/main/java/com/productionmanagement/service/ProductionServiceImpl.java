package com.productionmanagement.service;

import com.productionmanagement.domain.models.production.ProductionModel;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;

    @Autowired
    public ProductionServiceImpl(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Override
    public OperationResult<ProductionModel> createProduction(ProductionModel productionModel) {
        try {
            OperationResult<ProductionModel> result = validateProduction(productionModel);

            return result;
        } catch (Exception ex) {
            return OperationResult.Exception(ex);
        }
    }

    private OperationResult<ProductionModel> validateProduction(ProductionModel productionModel) {
        return null;
    }
}
