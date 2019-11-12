package com.productionmanagement.domain.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {
    private int id;
    private String uuid;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String id) {
        this.uuid = id;
    }
}
