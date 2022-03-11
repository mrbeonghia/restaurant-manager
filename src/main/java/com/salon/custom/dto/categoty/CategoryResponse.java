package com.salon.custom.dto.categoty;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.entities.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse extends BaseResponse {
    private List<Category> categoryDTOS;

    public CategoryResponse(List<Category> categories) {
        this.categoryDTOS = categories;
    }
}
