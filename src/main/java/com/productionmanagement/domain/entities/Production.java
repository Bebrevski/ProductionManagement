package com.productionmanagement.domain.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "productions")
@DynamicUpdate
public class Production extends BaseEntity{
    private String name;
    private LocalDate dateCreated;
    private String identifyingNumber;
    private String GPSCoordinates;
    private String fullAddress;
    private String description;
    private String email;
    private Boolean isActive;

    public Production() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "date_created", nullable = false)
    public LocalDate getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "identifying_number", unique = true)
    public String getIdentifyingNumber() {
        return this.identifyingNumber;
    }

    public void setIdentifyingNumber(String identifyingNumber) {
        this.identifyingNumber = identifyingNumber;
    }

    @Column(name = "GPS_coordinates")
    public String getGPSCoordinates() {
        return this.GPSCoordinates;
    }

    public void setGPSCoordinates(String GPSCoordinates) {
        this.GPSCoordinates = GPSCoordinates;
    }

    @Column(name = "full_address")
    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "is_active")
    public Boolean getActive() {
        return this.isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
