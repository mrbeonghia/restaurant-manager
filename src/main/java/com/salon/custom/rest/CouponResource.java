/*
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

    @Autowired
    private CouponService couponService;

    @GetMapping("v1/app/getAllCoupon")
    public ResponseEntity<CouponResponse> getAllCouponForApp(@RequestParam(name = "page", defaultValue = "1") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        CouponResponse couponResponse = couponService.getAllCouponForApp(pageable);
        return ResponseEntity.ok().body(couponResponse);
    }



    @GetMapping("v2/app/getAllCoupon")
    public ResponseEntity<CouponResponse> getAllCouponForGuest(@RequestParam(name = "page", defaultValue = "1") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        CouponResponse couponResponse = couponService.getAllCouponForUser(pageable);
        return ResponseEntity.ok().body(couponResponse);
    }

    @GetMapping("api/v2/app/getAllCoupon")
    public ResponseEntity<CouponResponse> getAllCouponForUserApp(@RequestParam(name = "page", defaultValue = "1") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        CouponResponse couponResponse = couponService.getAllCouponForUser(pageable);
        return ResponseEntity.ok().body(couponResponse);
    }

    @GetMapping("api/v1/admin/coupon")
    public ResponseEntity<CouponResponse> getAllCouponForAdmin(@RequestParam(name = "page", defaultValue = "1") int page,
                                                               @RequestParam(name = "size", defaultValue = "10") int size,
                                                               @RequestParam(name = "storeId", required = false) Long storeId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        CouponResponse couponResponse = couponService.getAllCouponForAdmin(userAdmin, pageable,storeId);
        return ResponseEntity.ok().body(couponResponse);
    }

    @PostMapping("api/v1/admin/coupon")
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody CouponRequest couponRequest) {
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        CouponResponse couponResponse = couponService.createCoupon(userAdmin, couponRequest);
        return ResponseEntity.ok().body(couponResponse);
    }

    @PutMapping("api/v1/admin/coupon")
    public ResponseEntity<CouponResponse> updateCoupon(@RequestBody CouponRequest couponRequest) {
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        CouponResponse couponResponse = couponService.updateCoupon(userAdmin, couponRequest);
        return ResponseEntity.ok().body(couponResponse);
    }

    @DeleteMapping("api/v1/admin/coupon")
    public ResponseEntity<CouponResponse> deleteCoupon(@RequestParam("couponId") Long couponId) {
        CouponResponse couponResponse = couponService.deleteCoupon(couponId);
        return ResponseEntity.ok().body(couponResponse);
    }

    @PutMapping("api/v2/admin/updateCouponSystemAdmin")
    public ResponseEntity<CouponResponse> updateCouponSystemAdmin() {
        CouponResponse couponResponse = couponService.updateStoreIdForCouponAll();
        return ResponseEntity.ok().body(couponResponse);
    }
}
*/
