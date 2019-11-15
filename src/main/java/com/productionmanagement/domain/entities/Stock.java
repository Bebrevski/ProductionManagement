package com.productionmanagement.domain.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "stocks")
@DynamicUpdate
public class Stock extends BaseEntity {
    private StockType stockType;
    private Production production;
    private LocalDate lastModified;
    private List<Material> materials;

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

    @ManyToOne(targetEntity = Production.class)
    @JoinColumn(name = "production_id", referencedColumnName = "id")
    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    @Column(name = "last_modified")
    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    @ManyToMany(targetEntity = Material.class)
    @JoinTable(
            name="stocks_materials",
            joinColumns = @JoinColumn(name = "stock_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id")
    )
    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}
