package com.salon.custom.dto.point.point_user;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointUserDTO {
    private Long id;
    private Long storeId;
    private String storeName;
    private Long staffId;
    private String staffName;
    private Long customerId;
    private String customerName;
    private String furiganaName;
    private Long numberOfPoints;
    private String pointStatus;
    private String pointType;
    private Float totalRating;
    private Date timeVisit;
    private Date timeConfirm;
    private String gachaName;

}
