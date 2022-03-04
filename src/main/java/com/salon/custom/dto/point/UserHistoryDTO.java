package com.salon.custom.dto.point;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserHistoryDTO {
    private Long pointId;
    private Long storeId;
    private String storeName;
    private Long customerId;
    private String customerName;
    private Date createdTime;
    private Long staffId;
    private String staffName;
    private Long pointCheckIn;
    private Long pointUse;
    private Long pointReview;
    private Long pointCheckInAndReview;
    private Long pointGacha;
    private Float totalRating;
    private boolean deletedByStore;

    public UserHistoryDTO(Long pointId, Long storeId, String storeName, Long customerId, String customerName, Date createdTime, Long staffId, String staffName, Long pointCheckIn, Long pointUse, Long pointReview, Long pointGacha) {
        this.pointId = pointId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.createdTime = createdTime;
        this.staffId = staffId;
        this.staffName = staffName;
        this.pointCheckIn = pointCheckIn;
        this.pointUse = pointUse;
        this.pointReview = pointReview;
        this.pointGacha = pointGacha;
    }

}
