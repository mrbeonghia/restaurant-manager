package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.categoty.CategoryResponse;
import com.salon.custom.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryResource extends BaseResource<CategoryService> {
    @GetMapping("api/getCategory")
    public ResponseEntity<CategoryResponse> getCategory() {
        CategoryResponse response = service.getListCategory();
        return ResponseEntity.ok().body(response);
    }
}
