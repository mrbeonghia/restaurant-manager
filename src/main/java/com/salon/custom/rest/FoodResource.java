package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.food.FoodResponse;
import com.salon.custom.dto.staff.StaffResponse;
import com.salon.custom.service.FoodService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodResource extends BaseResource<FoodService> {
    @GetMapping("api/getFood")
    public ResponseEntity<FoodResponse> getStaffForUserApp(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                                           @RequestParam(name = "page", defaultValue = "1") int page,
                                                           @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok().body(service.getListFood(categoryId, pageable));
    }
}
