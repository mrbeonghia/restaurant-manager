package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.categoty.CategoryRequest;
import com.salon.custom.dto.categoty.CategoryResponse;
import com.salon.custom.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryResource extends BaseResource<CategoryService> {
    @GetMapping("api/getCategory")
    public ResponseEntity<CategoryResponse> getCategory() {
        CategoryResponse response = service.getListCategory();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("api/createCategory")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        CategoryResponse response = service.createCategory(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("api/updateCategory")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest request) {
        CategoryResponse response = service.updateCategory(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("api/deleteCategory")
    public ResponseEntity<CategoryResponse> deleteCategory(@RequestParam(name = "id") Long id) {
        CategoryResponse response = service.deleteCategory(id);
        return ResponseEntity.ok().body(response);
    }
}
