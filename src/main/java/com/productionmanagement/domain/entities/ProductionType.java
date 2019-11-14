package com.productionmanagement.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="productionType")
public class ProductionType extends BaseEntity{
    private String name;
    private boolean isActive;

    public ProductionType() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_active")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
