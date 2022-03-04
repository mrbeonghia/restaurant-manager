package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.AuthenticationEventEntity;
import com.salon.custom.enums.UserType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthenticationEventRepository extends BaseRepository<AuthenticationEventEntity> {
    @Query(value = "select * from auth_event where (timeout * 60 - (UNIX_TIMESTAMP() - UNIX_TIMESTAMP(created_time))) > 0",
            nativeQuery = true)
    List<AuthenticationEventEntity> getAllWithValidateTimeout();

    List<AuthenticationEventEntity> findByUserId(Long userId);

    List<AuthenticationEventEntity> findByUserIdAndUserType(Integer userId, UserType userType);

    @Modifying
    @Query(value = "UPDATE AuthenticationEventEntity a SET a.isLogout = true WHERE a.userId = :userId and a.isDeleted = false ")
    int updateLogoutUser(@Param("userId") Long userId);
}
