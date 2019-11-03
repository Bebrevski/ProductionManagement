package com.productionmanagement.service;

import com.productionmanagement.domain.models.production.ProductionModel;
import com.productionmanagement.helpers.OperationResult;

public interface ProductionService {
    OperationResult<ProductionModel> createProduction(ProductionModel production);
}
