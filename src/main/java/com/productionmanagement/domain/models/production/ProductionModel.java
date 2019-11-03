package com.productionmanagement.domain.models.production;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ProductionModel {
    private String id;
    private String name;
    private String identifyingNumber;
    private LocalDate dateCreated;
    private String GPSCoordinates;
    private String fullAddress;
    private String description;
    private String email;
    private Boolean isActive;

    public ProductionModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId (String id) { this.id = id; }

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public LocalDate getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonProperty("GPSCoordinates")
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

    @JsonProperty("isActive")
    public Boolean getActive() {
        return this.isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
