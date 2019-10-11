package com.productionmanagement.domain.models.production;

public class ProductionBindingModel {
    private String name;
    private String identifyingNumber;
    private String GPSCoordinates;
    private String fullAddress;
    private String description;
    private String email;
    private Boolean isActive;

    public ProductionBindingModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifyingNumber() {
        return this.identifyingNumber;
    }

    public void setIdentifyingNumber(String identifyingNumber) {
        this.identifyingNumber = identifyingNumber;
    }

    public String getGPSCoordinates() {
        return this.GPSCoordinates;
    }

    public void setGPSCoordinates(String GPSCoordinates) {
        this.GPSCoordinates = GPSCoordinates;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return this.isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
