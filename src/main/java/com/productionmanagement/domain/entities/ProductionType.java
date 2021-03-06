package com.productionmanagement.domain.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="production_type")
@DynamicUpdate
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
