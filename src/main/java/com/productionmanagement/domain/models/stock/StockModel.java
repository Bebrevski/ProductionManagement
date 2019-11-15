package com.productionmanagement.domain.models.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.productionmanagement.domain.models.production.ProductionModel;

import java.time.LocalDate;
import java.util.List;

public class StockModel {
    private int id;
    private String uuid;
    private StockTypeModel stockType;
    private ProductionModel production;
    private LocalDate lastModified;
    private List<MaterialModel> materials;

    private int stockTypeId;


    public StockModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public StockTypeModel getStockType() {
        return stockType;
    }

    public void setStockType(StockTypeModel stockType) {
        this.stockType = stockType;
    }

    public ProductionModel getProduction() {
        return production;
    }

    public void setProduction(ProductionModel production) {
        this.production = production;
    }

    @JsonProperty("lastModified")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public List<MaterialModel> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialModel> materials) {
        this.materials = materials;
    }

    @JsonProperty("stockTypeId")
    public int getStockTypeId() {
        return stockTypeId;
    }

    public void setStockTypeId(int stockTypeId) {
        this.stockTypeId = stockTypeId;
    }
}
