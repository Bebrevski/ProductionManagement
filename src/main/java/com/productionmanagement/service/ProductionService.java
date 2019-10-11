package com.productionmanagement.service;

import com.productionmanagement.domain.models.production.ProductionServiceModel;

public interface ProductionService {
    ProductionServiceModel createProduction(ProductionServiceModel model);
}
