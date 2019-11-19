package com.productionmanagement.domain.models.stock;

import java.time.LocalDate;

public class MaterialModel {
    private int id;
    private String uuid;
    private String code;
    private Double quantity;
    private Double minimumQuantity;
    private LocalDate receivedOn;
    private UnitOfMeasureModel unitOfMeasure;
    private MaterialTypeModel materialType;

    public MaterialModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Double minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public LocalDate getReceivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
    }

    public UnitOfMeasureModel getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasureModel unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public MaterialTypeModel getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialTypeModel materialType) {
        this.materialType = materialType;
    }
}
