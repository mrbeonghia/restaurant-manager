package com.salon.custom.dto.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {
    private String staffName;
    private Long staffId;
    private Long storeId;
    private Integer rating1;
    private Integer rating2;
    private Integer rating3;
    private Integer rating4;
    private Integer rating5;
    private Integer rating6;
    private Integer rating7;
    private String comment;
    private String dateService;
    private Long pointId;
    private Long bookingId;

}
