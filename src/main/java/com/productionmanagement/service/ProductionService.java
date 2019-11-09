package com.productionmanagement.service;

import com.productionmanagement.domain.models.production.ProductionModel;
import com.productionmanagement.helpers.OperationResult;

import java.util.List;

public interface ProductionService {
    OperationResult<ProductionModel> submitProduction(ProductionModel production);

    OperationResult<List<ProductionModel>> getAllProductions();

    OperationResult<ProductionModel> getProductionData(String uuid);
}
