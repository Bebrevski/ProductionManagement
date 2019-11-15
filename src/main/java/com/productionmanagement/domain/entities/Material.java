package com.productionmanagement.domain.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "materials")
@DynamicUpdate
public class Material extends BaseEntity {
    private String code;
    private Double quantity;
    private Double minimumQuantity;
    private LocalDate receivedOn;
    private UnitOfMeasure unitOfMeasure;
    private MaterialType materialType;

    public Material() {
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "quantity")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Column(name = "minimum_quantity")
    public Double getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Double minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    @Column(name = "received_on")
    public LocalDate getReceivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
    }

    @ManyToOne(targetEntity = UnitOfMeasure.class)
    @JoinColumn(name = "unit_of_measure_id", referencedColumnName = "id")
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @ManyToOne(targetEntity = MaterialType.class)
    @JoinColumn(name = "material_type__id", referencedColumnName = "id")
    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}
