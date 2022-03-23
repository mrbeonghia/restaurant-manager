package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.categoty.CategoryRequest;
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

    public CategoryResponse createCategory(CategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        save(category);
        return new CategoryResponse(category);
    }

    public CategoryResponse updateCategory(CategoryRequest request){
        Category category = repository.findByIdAndDeletedFalse(request.getId());
        category.setName(request.getName());
        update(category);
        return new CategoryResponse(category);
    }

    public CategoryResponse deleteCategory(Long id){
        Category category = repository.findByIdAndDeletedFalse(id);
        category.setDeleted(true);
        update(category);
        return new CategoryResponse(category);
    }

    public  List<Category> findListCategory(){
        return repository.findByDeletedFalse();
    }

    public Category getById(Long id){
        return repository.findByIdAndDeletedFalse(id);
    }

}
