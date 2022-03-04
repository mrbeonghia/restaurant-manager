package com.salon.custom.dto.user.user_visit;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserVisitDTO {
    private Long userId;
    private Long pointId;
    private Long pointReview;
    private Long storeId;
    private String storeName;
    private String storeUrl;
    private String storePhone;
    private Long staffId;
    private String staffName;
    private Date dateVisit;
    private Boolean reviewed;
    private Float ratingAverage;
    private String reviewComment;
    private Boolean spinGacha;
    private Boolean sendByStore;
    private Boolean enableSpinGacha;

}
