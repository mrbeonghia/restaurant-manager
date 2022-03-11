package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.categoty.CategoryResponse;
import com.salon.custom.dto.user.UserResponse;
import com.salon.custom.entities.Category;
import com.salon.custom.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService<Category, CategoryRepository> {

    public CategoryResponse getListCategory(){
        List<Category> categories = repository.findByDeletedFalse();
        return new CategoryResponse(categories);
    }

}
