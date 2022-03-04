package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.dto.staff.StaffDTO;
import com.salon.custom.dto.staff.staff_user.StaffUserDTO;
import com.salon.custom.entities.StaffEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface StaffRepository extends BaseRepository<StaffEntity> {

    @Query(value = "SELECT new com.salon.custom.dto.staff.StaffDTO(se.id, se.name, se.avatarUrl, se.sex, se.birthday) " +
            "FROM StaffEntity se " +
            "WHERE se.storeId = :storeId AND se.isDeleted = false order by se.id DESC ")
    Page<StaffDTO> getStaffForUserApp(@Param("storeId") Long storeId, Pageable pageable);

    @Query(value = "SELECT distinct se " +
            "FROM StaffEntity  se " +
            "WHERE (:storeId is null OR se.storeId = :storeId ) " +
            "AND (:name is null OR se.name like concat('%', :name, '%')) ")
    Page<StaffEntity> searchStaff(@Param("name") String name, @Param("storeId") Long storeId, Pageable pageable);

    @Modifying
    @Query(value = "Update StaffEntity se SET se.isDeleted = true WHERE se.id = :staffId ")
    int deleteStaffForAdmin(@Param("staffId") Long staffId);

    StaffEntity findByIdAndIsDeletedFalse(Long staffId);

    List<StaffEntity> findByIdInAndIsDeletedFalse(Set<Long> staffIds);

    List<StaffEntity> findAllByIsDeletedFalse();

    @Query(value = "SELECT new com.salon.custom.dto.staff.StaffDTO(se.id, se.name, se.avatarUrl, se.sex, se.birthday) " +
            "FROM StaffEntity se " +
            "WHERE se.id IN :staffIds AND se.isDeleted = false ")
    List<StaffDTO> findByIdIn(@Param("staffIds") Set<Long> staffIds);

    @Query(value = "SELECT new com.salon.custom.dto.staff.StaffDTO(se.id, se.name, se.avatarUrl, se.sex, se.birthday) " +
            "FROM StaffEntity se " +
            "WHERE se.id = :staffId")
    StaffDTO findByStaffId(@Param("staffId") Long staffId);

/*    @Query(value = "SELECT new com.salon.custom.dto.staff.StaffDTO(se.id, se.name, se.avatarUrl, se.sex, se.phoneNumber, " +
            " se.birthday, se.monthDay, se.tueDay, se.wedDay, se.thuDay, se.friDay, se.satDay, se.sunDay) " +
            "FROM StaffEntity se " +
            "WHERE (:storeId is null OR se.storeId = :storeId ) " +
            "AND (:sex is null OR se.sex = :sex) AND se.isDeleted = false order by se.id DESC ")
    Page<StaffDTO> getAllStaffForAdmin(@Param("storeId") Long storeId, String sex, Pageable pageable);*/

    StaffEntity findByEmailAndIsDeletedFalse(String email);

    @Query(value = "SELECT * FROM salonapp.staff AS s WHERE s.phone_number = :phone AND s.is_deleted = false LIMIT 1", nativeQuery = true)
    StaffEntity findByPhoneNumber(@Param("phone") String phone);

    @Query(value = "SELECT se FROM StaffEntity se WHERE (:staffId is null OR se.id= :staffId) " +
            "AND (:staffName is null OR se.name like concat('%', :staffName, '%') ) " +
            "AND se.isDeleted = false ORDER BY se.id DESC ")
    Page<StaffEntity> findAllStaffByIdAndName(@Param("staffId") Long staffId, String staffName, Pageable pageable);

    @Query(value = "SELECT se FROM StaffEntity se WHERE " +
            "se.id in :staffIds AND se.isDeleted = false ORDER BY se.id DESC ")
    Page<StaffEntity> findAllStaffByStore(@Param("staffIds") List<Long> staffIds, Pageable pageable);

    @Query(value = "SELECT se FROM StaffEntity se WHERE se.storeId IN (:storeIds) AND se.name IN (:staffNames) " +
            "AND se.isDeleted = false ")
    Set<StaffEntity> findAllStaffByIds(@Param("storeIds") List<Long> storeIds, @Param("staffNames") List<String> staffNames);

}
