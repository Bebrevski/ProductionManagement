package com.productionmanagement.domain.models.stock;

public class MaterialModel {
    private String name;
    private boolean isActive;

    public MaterialModel() {
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
