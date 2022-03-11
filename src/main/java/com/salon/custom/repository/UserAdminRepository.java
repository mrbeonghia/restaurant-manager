/*
package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.UserAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAdminRepository extends BaseRepository<UserAdmin> {
    UserAdmin findByUserNameAndIsActiveTrue(String userName);

    List<UserAdmin> findByIdInAndIsDeletedFalse(List<Long> userIds);

    UserAdmin findByIdAndTypeAndIsDeletedFalse(Long id, String type);

    UserAdmin

    findByIdAndIsDeletedFalse(Long id);

    @Query(value = "SELECT ua FROM UserAdmin ua, StoreEntity se WHERE ua.id = se.ownerId AND se.id = :storeId AND ua.isDeleted = false ")
    UserAdmin findByStoreId(@Param("storeId") Long storeId);

    @Query(value = "SELECT count(ua.id) FROM UserAdmin ua WHERE ua.email = :email AND ua.isDeleted = false ")
    int findByEmailAndDeletedFalse(@Param("email") String email);

    @Modifying
    @Query(value = "Delete FROM UserAdmin ua WHERE ua.id = :ownerId ")
    int deleteAccountManagerStore(@Param("ownerId") Long ownerId);

    @Query(value = "SELECT ua FROM UserAdmin ua WHERE ua.email = :email AND ua.isDeleted = false AND ua.isActive = true ")
    UserAdmin findUserAdminByEmail(@Param("email") String email);

    @Query(value = "SELECT ua FROM UserAdmin ua WHERE ua.type = :type AND ua.storeId = :storeId AND ua.isDeleted = false ")
    Page<UserAdmin> findUserAdminInStore(@Param("storeId") Long storeId, @Param("type") String type, Pageable pageable);

    @Query(value = "SELECT ua FROM UserAdmin ua WHERE ua.type = :type AND ua.isDeleted = false ")
    List<UserAdmin> findAllUserAdminByType(@Param("type") String type);

}
*/
