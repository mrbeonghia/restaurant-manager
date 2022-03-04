package com.salon.custom.dto.point.point_send;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PointSendRequest {
    private Long id;
    private Long userId;
    private Long customerId;
    private String customerName;
    private Long storeId;
    private Long staffId;
    private String staffName;
    private Long numberOfPoints;
    private Date timeVisit;
    private Boolean spinGacha;
    private String pointSendType;
}
