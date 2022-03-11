package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.coupon.CouponDTO;
import com.salon.custom.dto.coupon.CouponResponse;
import com.salon.custom.entities.Coupon;
import com.salon.custom.repository.CouponRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CouponService extends BaseService<Coupon, CouponRepository> {

    public CouponResponse getListCoupon(Pageable pageable){
        Page<Coupon> coupons = repository.findAllByDeletedFalse(pageable);
        List<CouponDTO> couponDTOS = new ArrayList<>();
        coupons.forEach(coupon -> couponDTOS.add(toDTO(coupon)));
        return new CouponResponse(couponDTOS, populatePageDto(coupons));
    }

    private CouponDTO toDTO(Coupon coupon){
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setId(coupon.getId());
        couponDTO.setTitle(coupon.getTitle());
        couponDTO.setImageUrl(coupon.getImageUrl());
        couponDTO.setDescription(coupon.getDescription());
        couponDTO.setStartDate(coupon.getStartDate());
        couponDTO.setEndDate(coupon.getEndDate());
        return couponDTO;
    }
}
