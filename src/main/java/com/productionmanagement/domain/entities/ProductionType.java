package com.productionmanagement.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="productionType")
public class ProductionType extends BaseEntity{
    private String name;
    private boolean isActive;

    public ProductionType() {
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
