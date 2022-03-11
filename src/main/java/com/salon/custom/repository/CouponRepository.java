/*
package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CouponRepository extends BaseRepository<Coupon> {

    @Query(value = "SELECT ce " +
            "FROM Coupon ce " +
            "WHERE ce.endDate >= :dateNow " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC ")
    Page<Coupon> findAllEndDateGreatThanDateNow(@Param("dateNow") Date dateNow, Pageable pageable);


    @Query(value = "SELECT ce " +
            "FROM Coupon ce " +
            "WHERE ce.endDate >= :dateNow AND ce.startDate <= :dateNow AND ce.storeId IN (:storeIds) " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC ")
    Page<Coupon> findAllCouponByDate(@Param("dateNow") Date dateNow, @Param("storeIds") List<Long> storeIds,
                                     Pageable pageable);

    @Query(value = "SELECT ce " +
            "FROM Coupon ce " +
            "WHERE ce.endDate >= :dateNow AND ce.startDate <= :dateNow AND ce.storeId IN (:storeIds) " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC ")
    List<Coupon> findAllCouponByDate(@Param("dateNow") Date dateNow, @Param("storeIds") List<Long> storeIds);

    @Query(value = "SELECT ce " +
            "FROM Coupon ce " +
            "WHERE ce.storeId IN :storeId " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC  ")
    Page<Coupon> findAllForStoreAdmin(@Param("storeId") List<Long> storeIds, Pageable pageable);

    @Query(value = "SELECT ce " +
            "FROM Coupon ce WHERE (ce.storeId is null OR ce.storeId = :storeId) " +
            "AND ce.isDeleted = false ORDER BY ce.id DESC  ")
    Page<Coupon> findAllForAdmin(@Param("storeId") Long storeId, Pageable pageable);

    Page<Coupon> findAllByIsDeletedFalseOrderByIdDesc(Pageable pageable);

    List<Coupon> findAllByIsDeletedFalse();

    Coupon findByIdAndIsDeletedFalse(Long couponId);

    List<Coupon> findByIdIn(List<Long> couponIds);
}
*/
