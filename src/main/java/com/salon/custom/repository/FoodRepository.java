package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.Food;
import com.salon.custom.entities.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends BaseRepository<Food> {

    Food findByIdAndDeletedFalse(Long staffId);

    Page<Food> findByDeletedFalse(Pageable pageable);

    Page<Food> findByCategoryAndDeletedFalse(Pageable pageable);

}
