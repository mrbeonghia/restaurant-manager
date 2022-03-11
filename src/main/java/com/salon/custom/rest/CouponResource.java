package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.coupon.CouponRequest;
import com.salon.custom.dto.coupon.CouponResponse;
import com.salon.custom.entities.UserAdmin;
import com.salon.custom.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CouponResource extends BaseResource<CouponService> {


    @GetMapping("v1/app/getAllCoupon")
    public ResponseEntity<CouponResponse> getAllCoupon(@RequestParam(name = "page", defaultValue = "1") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        CouponResponse couponResponse = service.getListCoupon(pageable);
        return ResponseEntity.ok().body(couponResponse);
    }


}
