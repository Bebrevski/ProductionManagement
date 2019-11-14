package com.productionmanagement.domain.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stocks")
@DynamicUpdate
public class Stock extends BaseEntity {
    private StockType stockType;
    private String code;
    private Double quantity;
    private Double minimumQuantity;
    private LocalDate receivedOn;
    private UnitOfMeasure unitOfMeasure;
    private Production production;

    public Stock() {
    }

    @ManyToOne(targetEntity = StockType.class)
    @JoinColumn(name = "stock_type_id", referencedColumnName = "id")
    public StockType getStockType() {
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "quantity", nullable = false)
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

    @ManyToOne(targetEntity = Production.class)
    @JoinColumn(name = "production_id", referencedColumnName = "id")
    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }
}
