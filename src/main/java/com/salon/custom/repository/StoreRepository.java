/*
package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.dto.store.StoreDTO;
import com.salon.custom.entities.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StoreRepository extends BaseRepository<StoreEntity> {

    @Query(value = "SELECT s FROM StoreEntity s WHERE s.isDeleted = false order by s.position DESC ")
    Page<StoreEntity> getAllStoreForApp(Pageable pageable);

    @Query(value = "SELECT s FROM StoreEntity s WHERE s.isBooking = true AND s.isDeleted = false order by s.position DESC ")
    List<StoreEntity> getAllStoreBookForApp();

    @Query(value = "SELECT s FROM StoreEntity s WHERE s.isDeleted = false order by s.position DESC ")
    List<StoreEntity> getAllStoreBooking();

    @Query(value = "SELECT s FROM StoreEntity s WHERE s.isDeleted = false order by s.name ASC ")
    Page<StoreEntity> getAllStoreFilterForAdmin(Pageable pageable);

    StoreEntity findByIdAndIsDeletedFalse(Long storeId);

    @Query(value = "SELECT s.id FROM StoreEntity s WHERE s.isDeleted = false And s.ownerId =:ownerId ")
    Long findByOwnerId(@Param("ownerId") Long ownerId);

    @Query(value = "SELECT new com.salon.custom.dto.store.StoreDTO(s.id, s.name, s.openTime, s.closeTime) " +
            "FROM StoreEntity s WHERE s.id = :storeId ")
    StoreDTO findByStoreId(@Param("storeId") Long storeId);

    List<StoreEntity> findByIdInAndIsDeletedFalse(Set<Long> storeIds);

    List<StoreEntity> findByIdInAndIsDeletedFalse(List<Long> storeIds);

    List<StoreEntity> findByIdIn(Set<Long> storeIds);

    List<StoreEntity> findAllByIsDeletedFalse();

    StoreEntity findByQrCodeUUIDAndIsDeletedFalse(String qrCodeUUID);

    StoreEntity findByOwnerIdAndIsDeletedFalse(Long ownerId);

    @Query(value = "SELECT count(s.id) FROM StoreEntity s ")
    int countStore();

    @Query(value = "SELECT s.name FROM StoreEntity s WHERE s.id = :storeId AND s.isDeleted = false")
    String findStoreNameById(@Param("storeId") Long storeId);


    @Query(value = "SELECT s.bed FROM StoreEntity s WHERE s.id = ?1 AND s.isDeleted = false ")
    Integer findMaxBedOfStore(Long storeId);

}
*/
