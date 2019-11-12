package com.productionmanagement.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nomenclatures")
public class Nomenclature  extends BaseEntity{
    private String tableName;
    private Boolean isVisible;
    private String titleToBeDisplayed;
    private String connectedTableName;
    private String connectedTableIdColumnName;

    public Nomenclature() {
    }

    @Column(name = "table_name", nullable = false)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "is_visible", nullable = false)
    public Boolean isVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    @Column(name = "title_to_be_displayed", nullable = false)
    public String getTitleToBeDisplayed() {
        return titleToBeDisplayed;
    }

    public void setTitleToBeDisplayed(String titleToBeDisplayed) {
        this.titleToBeDisplayed = titleToBeDisplayed;
    }

    @Column(name = "connected_table_name", nullable = false)
    public String getConnectedTableName() {
        return connectedTableName;
    }

    public void setConnectedTableName(String connectedTableName) {
        this.connectedTableName = connectedTableName;
    }

    @Column(name = "connected_table_ID_column_name", nullable = false)
    public String getConnectedTableIdColumnName() {
        return connectedTableIdColumnName;
    }

    public void setConnectedTableIdColumnName(String connectedTableIdColumnName) {
        this.connectedTableIdColumnName = connectedTableIdColumnName;
    }
}
