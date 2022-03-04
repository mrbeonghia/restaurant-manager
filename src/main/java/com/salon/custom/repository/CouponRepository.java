package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.dto.coupon.CouponDTO;
import com.salon.custom.entities.CouponEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CouponRepository extends BaseRepository<CouponEntity> {

    @Query(value = "SELECT ce " +
            "FROM CouponEntity ce " +
            "WHERE ce.endDate >= :dateNow " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC ")
    Page<CouponEntity> findAllEndDateGreatThanDateNow(@Param("dateNow") Date dateNow, Pageable pageable);


    @Query(value = "SELECT ce " +
            "FROM CouponEntity ce " +
            "WHERE ce.endDate >= :dateNow AND ce.startDate <= :dateNow AND ce.storeId IN (:storeIds) " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC ")
    Page<CouponEntity> findAllCouponByDate(@Param("dateNow") Date dateNow, @Param("storeIds") List<Long> storeIds,
                                           Pageable pageable);

    @Query(value = "SELECT ce " +
            "FROM CouponEntity ce " +
            "WHERE ce.endDate >= :dateNow AND ce.startDate <= :dateNow AND ce.storeId IN (:storeIds) " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC ")
    List<CouponEntity> findAllCouponByDate(@Param("dateNow") Date dateNow, @Param("storeIds") List<Long> storeIds);

    @Query(value = "SELECT ce " +
            "FROM CouponEntity ce " +
            "WHERE ce.storeId IN :storeId " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC  ")
    Page<CouponEntity> findAllForStoreAdmin(@Param("storeId") List<Long> storeIds, Pageable pageable);

    @Query(value = "SELECT ce " +
            "FROM CouponEntity ce WHERE (ce.storeId is null OR ce.storeId = :storeId) " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC  ")
    Page<CouponEntity> findAllForAdmin(@Param("storeId") Long storeId, Pageable pageable);

    Page<CouponEntity> findAllByIsDeletedFalseOrderByIdDesc(Pageable pageable);

    List<CouponEntity> findAllByIsDeletedFalse();

    CouponEntity findByIdAndIsDeletedFalse(Long couponId);

    List<CouponEntity> findByIdIn(List<Long> couponIds);
}
