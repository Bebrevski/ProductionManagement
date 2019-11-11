package com.productionmanagement.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nomenclatures")
public class Nomenclature  extends BaseEntity{
    private String tableName;
    private boolean isVisible;
    private String titleToBeDisplayed;
    private String ConnectedTableName;
    private String ConnectedTableIdColumnName;

    //Don't forget isActive to Prodcution
}
