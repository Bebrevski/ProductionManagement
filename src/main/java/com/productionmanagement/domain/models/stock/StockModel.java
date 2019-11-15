package com.productionmanagement.domain.models.stock;

import com.productionmanagement.domain.models.production.ProductionModel;

import java.time.LocalDate;

public class StockModel {
    //private StockTypeModel stockType;
    private ProductionModel production;
    private LocalDate lastModified;
    //private List<MaterialModel> materials;


    public StockModel() {
    }

    public ProductionModel getProduction() {
        return production;
    }

    public void setProduction(ProductionModel production) {
        this.production = production;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }
}
