package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.coupon.CouponDTO;
import com.salon.custom.dto.coupon.CouponRequest;
import com.salon.custom.dto.coupon.CouponResponse;
import com.salon.custom.entities.Coupon;
import com.salon.custom.repository.CouponRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CouponService extends BaseService<Coupon, CouponRepository> {

    public CouponResponse getListCoupon(Pageable pageable){
        Page<Coupon> coupons = repository.findAllByDeletedFalse(pageable);
        List<CouponDTO> couponDTOS = new ArrayList<>();
        coupons.forEach(coupon -> couponDTOS.add(toDTO(coupon)));
        return new CouponResponse(couponDTOS, populatePageDto(coupons));
    }

    public CouponResponse getCouponAvailable(){
        List<Coupon> coupons = repository.findCouponByDate(new Date());
        List<CouponDTO> couponDTOS = new ArrayList<>();
        coupons.forEach(coupon -> couponDTOS.add(toDTO(coupon)));
        return new CouponResponse(couponDTOS);
    }

    public CouponResponse createCoupon(CouponRequest request){
        Coupon coupon = new Coupon();
        toEntity(request, coupon);
        return new CouponResponse(toDTO(coupon));
    }

    public CouponResponse updateCoupon(CouponRequest request){
        Coupon coupon = repository.findByIdAndDeletedFalse(request.getId());
        if (coupon == null){
            return new CouponResponse("This coupon not found", 4004);
        }
        if (request.getEndDate().before(request.getStartDate())){
            return new CouponResponse("Start date must before end date", 4004);
        }
        toEntity(request, coupon);
        update(coupon);
        return new CouponResponse(toDTO(coupon));
    }

    public CouponResponse deleteCoupon(Long id){
        Coupon coupon = repository.findByIdAndDeletedFalse(id);
        if (coupon == null){
            return new CouponResponse("This coupon not found", 4004);
        }
        coupon.setDeleted(true);
        update(coupon);
        return new CouponResponse(toDTO(coupon));
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

    private void toEntity(CouponRequest request, Coupon coupon){
        coupon.setTitle(request.getTitle());
        coupon.setDescription(request.getDescription());
        coupon.setStartDate(request.getStartDate());
        coupon.setEndDate(request.getEndDate());
        coupon.setDiscount(request.getDiscount());
    }
}
