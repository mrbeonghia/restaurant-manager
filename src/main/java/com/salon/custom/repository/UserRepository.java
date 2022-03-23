package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.dto.user.UserDTO;
import com.salon.custom.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface





UserRepository extends BaseRepository<UserEntity> {

    UserEntity findByPhoneNumberAndDeletedFalse(String phone);

    UserEntity findByIdAndDeletedFalse(Long id);

    @Query(value = "SELECT u FROM UserEntity u WHERE " +
            "( :search is null or u.name like concat('%', :search, '%') " +
            "OR u.email like concat('%', :search, '%') " +
            "OR u.phoneNumber like concat('%', :search, '%')) " +
            "AND u.deleted = false ORDER BY u.id DESC ")
    Page<UserEntity> searchUser(@Param("search") String search, Pageable pageable);

    Page<UserEntity> findByDeletedFalseOrderByIdDesc(Pageable pageable);

}
