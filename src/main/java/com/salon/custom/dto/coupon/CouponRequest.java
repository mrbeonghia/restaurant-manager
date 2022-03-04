package com.salon.custom.dto.coupon;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CouponRequest {
    private Long id;
    private Long storeId;
    private String title;
    private String imageUrl;
    private String description;
    private Date startDate;
    private Date endDate;
    private Long point;

}
