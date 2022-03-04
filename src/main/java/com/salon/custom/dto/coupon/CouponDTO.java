package com.salon.custom.dto.coupon;

import com.salon.custom.dto.store.StoreDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CouponDTO {
    private Long id;
    private Long storeId;
    private String storeName;
    private String title;
    private String imageUrl;
    private String description;
    private Date startDate;
    private Date endDate;
    private StoreDTO store;
    private Long point;

}
