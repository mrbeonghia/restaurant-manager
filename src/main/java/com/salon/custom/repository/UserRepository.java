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

    UserEntity findUserByUserName(String username);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.email = ?1 AND u.isDeleted = false AND u.isActive = true ")
    UserEntity findUserByEmail(String email);

    UserEntity findByEmailAndIsActiveTrue(String email);

    UserEntity findByIdAndIsDeletedFalse(Long id);

    @Query(value = "SELECT new com.salon.custom.dto.user.UserDTO(u.id, u.name, u.avatarUrl, u.phoneNumber, u.sex, u.email) FROM UserEntity u " +
            "WHERE u.id = :id ")
    UserDTO findByUserId(@Param("id") Long id);

    @Modifying
    @Query(value = "Delete FROM UserEntity u  " +
            "WHERE u.email = :email " +
            "AND u.isDeleted = true ")
    int deleteUserOldDeleted(@Param("email") String email);


    @Query(value = "SELECT u FROM UserEntity u WHERE " +
            "( :storeId is null or u.storeId = :storeId ) " +
            "AND ( :search is null or u.name like concat('%', :search, '%') OR u.email like concat('%', :search, '%') " +
            "OR u.phoneNumber like concat('%', :search, '%') OR u.furiganaName like concat('%', :search, '%') " +
            "OR u.customerId like concat('%', :search, '%') ) " +
            "AND ( :sex is null or u.sex = :sex) AND u.isDeleted = false ")
    Page<UserEntity> findAllAndIsDeletedFalse(@Param("search") String search, @Param("storeId") Long storeId,@Param("sex") String sex, Pageable pageable);

    @Query(value = "SELECT u FROM UserEntity u WHERE " +
            "( :storeId is null or u.storeId = :storeId ) " +
            "AND  ( :search is null or u.name like concat('%', :search, '%') OR u.email like concat('%', :search, '%') " +
            "OR u.phoneNumber like concat('%', :search, '%') OR u.furiganaName like concat('%', :search, '%') " +
            "OR u.customerId like concat('%', :search, '%') ) " +
            "AND ( :sex is null or u.sex = :sex) AND u.isDeleted = false ")
    Page<UserEntity> findAllSortByCreatedTime(@Param("search") String search, @Param("storeId") Long storeId,@Param("sex") String sex, Pageable pageable);


    @Query(value = "SELECT u FROM UserEntity u " +
            "WHERE ( u.name like concat('%', :search, '%') or u.email like concat('%', :search, '%') " +
            "or u.phoneNumber like concat('%', :search, '%') or u.furiganaName like concat('%', :search, '%') ) " +
            "AND u.isDeleted = false ORDER BY u.id DESC ")
    Page<UserEntity> findUserAppByNamOrPhoneOrEmailForAdmin(@Param("search") String search, Pageable pageable);

    @Query(value = "SELECT distinct u.id FROM UserEntity u " +
            "WHERE u.storeId = :storeId " +
            "AND u.isDeleted = false ")
    Set<Long> findByStoreIdAndIsDeletedFalse(@Param("storeId") Long storeId);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.storeId IN (?1) AND u.isDeleted = false ")
    List<UserEntity> findByStoreIds(List<Long> storeIds);

    List<UserEntity> findByIdIn(Set<Long> userIds);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.userName = :username AND u.isDeleted = false AND u.isActive = true ")
    UserEntity findByUserName(String username);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.id = :id AND u.isDeleted = false AND u.isActive = true ")
    UserEntity findCustomerById(@Param("id") Long id);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.isDeleted = false ")
    List<UserEntity> findAllUser();

    @Query(value = "SELECT u FROM UserEntity u WHERE u.customerId = :customerId AND u.isDeleted = false")
    UserEntity findByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "SELECT u.customerId FROM UserEntity u WHERE u.id = :id AND u.isDeleted = false AND u.isActive = true ")
    Long findCustomerIdById(@Param("id") Long id);

    @Query(value = "SELECT u.id FROM UserEntity u WHERE u.isDeleted = false AND u.isActive = true ")
    Set<Long> findAllUserIds();

    @Query(value = "SELECT u FROM UserEntity u WHERE " +
            "( :storeId is null or u.storeId = :storeId ) " +
            "AND ( :search is null or u.name like concat('%', :search, '%') or u.email like concat('%', :search, '%') or u.phoneNumber like concat('%', :search, '%') ) " +
            "AND ( :sex is null or u.sex = :sex) AND u.isDeleted = false ")
    List<UserEntity> findAllListUserAsc(@Param("search") String search, @Param("storeId") Long storeId, @Param("sex") String sex);

    @Query(value = "SELECT u FROM UserEntity u WHERE " +
            "( :storeId is null or u.storeId = :storeId ) " +
            "AND ( :search is null or u.name like concat('%', :search, '%') or u.email like concat('%', :search, '%') or u.phoneNumber like concat('%', :search, '%') ) " +
            "AND ( :sex is null or u.sex = :sex) AND u.isDeleted = false ")
    List<UserEntity> findAllListUserDesc(@Param("search") String search, @Param("storeId") Long storeId,@Param("sex") String sex);

    @Query(value = "SELECT u.phoneNumber FROM UserEntity u WHERE u.phoneNumber = ?1 AND NOT u.id = ?2 AND u.isDeleted = false ")
    List<String> findPhoneNumberExist(String phoneNumber, Long id);

    @Query(value = "SELECT u.phoneNumber FROM UserEntity u WHERE u.phoneNumber = ?1 AND u.isDeleted = false ")
    List<String> findPhoneNumberExist(String phoneNumber);

    @Query(value = "SELECT u FROM UserEntity u WHERE (u.email in ?1 OR u.phoneNumber in ?2) AND u.isDeleted = false")
    List<UserEntity> findByEmailOrPhoneIn(List<String> emails, List<String> phones);

    List<UserEntity> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);
}
