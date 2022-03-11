package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "table_entity")
@Getter
@Setter
public class TableEntity extends BaseEntity {

    private String name;

    private Integer seat;

    private boolean available;

}
