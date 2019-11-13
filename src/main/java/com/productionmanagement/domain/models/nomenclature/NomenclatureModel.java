package com.productionmanagement.domain.models.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NomenclatureModel {
    private int id;
    private String uuid;
    private String name;
    private boolean isActive;
    private int nomenclatureID;

    public NomenclatureModel() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("active")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @JsonProperty("nomenclatureID")
    public int getNomenclatureID() {
        return nomenclatureID;
    }

    public void setNomenclatureID(int nomenclatureID) {
        this.nomenclatureID = nomenclatureID;
    }
}
