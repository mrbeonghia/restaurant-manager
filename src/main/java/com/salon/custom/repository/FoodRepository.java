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

    Food findByIdAndDeletedFalse(Long staffId);

    @Query(value = "SELECT f FROM Food f WHERE ?1 is null OR f.category.id = ?1 " +
            "AND f.deleted = false ")
    Page<Food> findByCategoryId(Long categoryId, Pageable pageable);


}
