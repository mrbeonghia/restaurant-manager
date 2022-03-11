package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends BaseRepository<Staff> {

    Staff findByIdAndDeletedFalse(Long staffId);

    Page<Staff> findByDeletedFalse(Pageable pageable);

    Staff findByPhoneNumberAndDeletedFalse(String phoneNumber);

}
