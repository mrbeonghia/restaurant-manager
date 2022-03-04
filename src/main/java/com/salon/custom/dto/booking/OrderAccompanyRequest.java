package com.salon.custom.dto.booking;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderAccompanyRequest {
    private String customerName;
    private String customerPhone;
    private boolean chooseStaff;
    private Long staffId;
    private Integer bookingMinute;
    private boolean favourite;
    private Long comboId;
    private Set<Long> comboPackIds;
    private String note;
    private Long price;
}
