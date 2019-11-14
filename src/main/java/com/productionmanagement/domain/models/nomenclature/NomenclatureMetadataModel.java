package com.productionmanagement.domain.models.nomenclature;

public class NomenclatureMetadataModel {
    private int id;
    private String uuid;
    private String titleToBeDisplayed;
    private String tableName;
    private String connectedTableName;
    private String connectedTableIdColumnName;

    public NomenclatureMetadataModel() {
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

    public String getTitleToBeDisplayed() {
        return titleToBeDisplayed;
    }

    public void setTitleToBeDisplayed(String titleToBeDisplayed) {
        this.titleToBeDisplayed = titleToBeDisplayed;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getConnectedTableName() {
        return connectedTableName;
    }

    public void setConnectedTableName(String connectedTableName) {
        this.connectedTableName = connectedTableName;
    }

    public String getConnectedTableIdColumnName() {
        return connectedTableIdColumnName;
    }

    public void setConnectedTableIdColumnName(String connectedTableIdColumnName) {
        this.connectedTableIdColumnName = connectedTableIdColumnName;
    }
}
