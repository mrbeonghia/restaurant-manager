package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.food.FoodDTO;
import com.salon.custom.dto.food.FoodRequest;
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
        Page<Food> foods;
        if (categoryId == null) {
            foods = repository.findByDeletedFalseOrderById(pageable);
        } else {
            foods = repository.findByCategoryId(categoryId, pageable);
        }
        List<FoodDTO> foodDTOS = new ArrayList<>();
        List<Category> categories = categoryService.findListCategory();
        Map<Long, Category> idToCategory = categories.stream()
                .collect(Collectors.toMap(Category::getId, Function.identity()));
        foods.forEach(food -> {
            FoodDTO foodDTO = toDTO(food);
            Category category = idToCategory.get(food.getCategory().getId());
            if (category != null){
                foodDTO.setCategory(category.getName());
            }
            foodDTOS.add(foodDTO);

        });
        return new FoodResponse(foodDTOS, populatePageDto(foods));
    }

    public FoodResponse createFood(FoodRequest foodRequest){
        Food food = new Food();
        Food foodExist = repository.findByNameAndDeletedFalse(foodRequest.getName());
        if (foodExist != null){
            return new FoodResponse("This food already exist", 4005);
        }
        toEntity(foodRequest, food);
        save(food);
        return new FoodResponse(toDTO(food));
    }

    public FoodResponse updateFood(FoodRequest foodRequest){
        Food food = repository.findByIdAndDeletedFalse(foodRequest.getId());
        if (food == null){
            return new FoodResponse("This food not found", 4004);
        }
        toEntity(foodRequest, food);
        update(food);
        return new FoodResponse(toDTO(food));
    }

    public FoodResponse deleteFood(Long id){
        Food food = repository.findByIdAndDeletedFalse(id);
        if (food == null){
            return new FoodResponse("This food not found", 4004);
        }
        food.setDeleted(true);
        update(food);
        return new FoodResponse(toDTO(food));
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

    private void toEntity(FoodRequest request, Food food){
        food.setName(request.getName());
        food.setDescription(request.getDescription());
        food.setImageUrl(request.getImageUrl());
        food.setPrice(request.getPrice());
        food.setDescription(request.getDescription());
        food.setCategory(categoryService.getById(request.getCategoryId()));
    }
}
