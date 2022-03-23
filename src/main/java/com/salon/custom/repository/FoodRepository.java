package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.Food;
import com.salon.custom.entities.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends BaseRepository<Food> {

    Food findByIdAndDeletedFalse(Long id);

    @Query(value = "SELECT f FROM Food f WHERE f.category.id = (?1) " +
            "AND f.deleted = false ORDER BY f.id")
    Page<Food> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Food> findByDeletedFalseOrderById(Pageable pageable);

    Food findByNameAndDeletedFalse(String name);

}
