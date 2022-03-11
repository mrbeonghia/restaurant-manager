package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "food")
@Getter
@Setter
public class Food extends BaseEntity {
    private String name;

    private String description;

    private String imageUrl;

    private Long price;

    private boolean available;

    @ManyToOne(targetEntity = Category.class)
    private Category category;
}
