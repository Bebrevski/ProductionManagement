package com.productionmanagement.domain.models.nomenclature;

public class NomenclatureMetadataModel {
    private int id;
    private String uuid;
    private String titleToBeDisplayed;

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
}
