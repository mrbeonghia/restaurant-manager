package com.salon.custom.dto.point;

import com.salon.custom.dto.store.ImageStore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PointDTO {
    private Long id;
    private Long storeId;
    private String storeName;
    private List<ImageStore> storeImages;
    private Long staffId;
    private String staffName;
    private Long customerId;
    private String customerName;
    private Long pointCheckIn;
    private String pointCheckInStatus;
    private Long pointUse;
    private String pointUseStatus;
    private String pointUseType;
    private Long pointGacha;
    private Long pointReview;
    private Date createdTime;
    private Long customerPoint;
    private Boolean sendByStore;
    private Long pointReviewReceived;
    private Boolean isSeen;
    private String pointSendType;

}
