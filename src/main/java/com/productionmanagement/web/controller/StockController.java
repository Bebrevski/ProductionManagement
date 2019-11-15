package com.productionmanagement.web.controller;

import com.productionmanagement.domain.models.stock.StockModel;
import com.productionmanagement.helpers.KeyValuePair;
import com.productionmanagement.helpers.OperationResult;
import com.productionmanagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/getStocks/{productionUuid}")
    public OperationResult<List<StockModel>> getStocks(@PathVariable String productionUuid) {
        return this.stockService.getStocks(productionUuid);
    }

    //Nomenclatures
    @GetMapping("/getStockTypes")
    public OperationResult<List<KeyValuePair<Integer, String>>> getStockTypes() {
        return this.stockService.getStockTypes();
    }

    @GetMapping("/getMaterialTypes")
    public OperationResult<List<KeyValuePair<Integer, String>>> getMaterialTypes() {
        return this.stockService.getMaterialTypes();
    }

    @GetMapping("/getUnitsOfMeasure")
    public OperationResult<List<KeyValuePair<Integer, String>>> getUnitsOfMeasure() {
        return this.stockService.getUnitsOfMeasure();
    }
    //End of nomenclatures
}
