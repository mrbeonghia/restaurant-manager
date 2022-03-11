package com.salon.custom.dto.food;

import com.salon.custom.entities.Category;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class FoodDTO {
    private Long id;
    private String name;

    private String description;

    private String imageUrl;

    private Long price;

    private boolean available;

    private String category;
}
