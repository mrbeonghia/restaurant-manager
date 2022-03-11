package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.Staff;
import com.salon.custom.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends BaseRepository<Staff> {

    Staff findByIdAndDeletedFalse(Long staffId);

    Page<Staff> findByDeletedFalse(Pageable pageable);

    Staff findByPhoneNumberAndDeletedFalse(String phoneNumber);

    @Query(value = "SELECT u FROM Staff u WHERE " +
            "( :search is null or u.name like concat('%', :search, '%') " +
            "OR u.email like concat('%', :search, '%') " +
            "OR u.phoneNumber like concat('%', :search, '%')) " +
            "AND u.deleted = false ")
    Page<Staff> searchStaff(@Param("search") String search, Pageable pageable);

}
