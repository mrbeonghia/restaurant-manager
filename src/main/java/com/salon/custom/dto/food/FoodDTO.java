package com.salon.custom.dto.food;

import com.salon.custom.entities.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
public class FoodDTO {
    private Long id;
    private String name;

    private String description;

    private String imageUrl;

    private Long price;

    private boolean available;

    private String category;
}
