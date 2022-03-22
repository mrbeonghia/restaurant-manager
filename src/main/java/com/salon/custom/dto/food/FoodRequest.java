package com.salon.custom.dto.food;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequest {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Long price;
    private boolean available;
    private Long categoryId;
}
