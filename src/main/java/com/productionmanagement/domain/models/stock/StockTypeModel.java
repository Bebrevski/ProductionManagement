package com.productionmanagement.domain.models.stock;

public class StockTypeModel {
    private String name;
    private boolean isActive;

    public StockTypeModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
