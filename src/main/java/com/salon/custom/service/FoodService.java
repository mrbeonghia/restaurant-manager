package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.food.FoodDTO;
import com.salon.custom.dto.food.FoodResponse;
import com.salon.custom.entities.Category;
import com.salon.custom.entities.Food;
import com.salon.custom.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FoodService extends BaseService<Food, FoodRepository> {

    @Autowired
    private CategoryService categoryService;

    public FoodResponse getListFood(Long categoryId, Pageable pageable) {
        Page<Food> foods = repository.findByCategoryId(categoryId, pageable);
        List<FoodDTO> foodDTOS = new ArrayList<>();
        List<Category> categories = categoryService.findListCategory();
        Map<Long, Category> idToCategory = categories.stream()
                .collect(Collectors.toMap(Category::getId, Function.identity()));
        foods.forEach(food -> {
            FoodDTO foodDTO = toDTO(food);
            foodDTO.setCategory(idToCategory.get(food.getCategory().getId()).getName());
            foodDTOS.add(foodDTO);

        });
        return new FoodResponse(foodDTOS, populatePageDto(foods));
    }

    private FoodDTO toDTO(Food food) {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setId(food.getId());
        foodDTO.setName(food.getName());
        foodDTO.setDescription(food.getDescription());
        foodDTO.setImageUrl(food.getImageUrl());
        foodDTO.setPrice(food.getPrice());
        foodDTO.setAvailable(food.isAvailable());
        return foodDTO;
    }
}
