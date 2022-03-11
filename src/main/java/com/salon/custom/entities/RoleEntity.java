package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role_entity")
@Getter
@Setter
public class RoleEntity extends BaseEntity {
    private String name;
}
