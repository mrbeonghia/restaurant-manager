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
            "WHERE ce.endDate >= :dateNow AND ce.startDate <= :dateNow " +
            "AND ce.deleted = false ORDER BY ce.id DESC ")
    List<Coupon> findCouponByDate(@Param("dateNow") Date dateNow);

    Page<Coupon> findAllByDeletedFalse(Pageable pageable);

    List<Coupon> findAllByDeletedFalse();

    Coupon findByIdAndDeletedFalse(Long couponId);


}
