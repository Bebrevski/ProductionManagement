package com.productionmanagement.service;

import com.productionmanagement.domain.models.stock.StockModel;
import com.productionmanagement.helpers.KeyValuePair;
import com.productionmanagement.helpers.OperationResult;

import java.util.List;

public interface StockService {
    OperationResult<List<KeyValuePair<Integer, String>>> getStockTypes();

    OperationResult<List<KeyValuePair<Integer, String>>> getMaterialTypes();

    OperationResult<List<KeyValuePair<Integer, String>>> getUnitsOfMeasure();

    OperationResult<List<StockModel>> getStocks(String productionUuid);
}
