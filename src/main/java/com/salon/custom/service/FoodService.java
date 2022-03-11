package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.entities.Food;
import com.salon.custom.repository.FoodRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodService extends BaseService<Food, FoodRepository> {
}
