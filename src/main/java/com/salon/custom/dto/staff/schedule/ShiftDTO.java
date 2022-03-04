package com.salon.custom.dto.staff.schedule;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftDTO {
    private Long id;
    private Long storeId;
    private String storeName;
    private String openTimeStore;
    private String closeTimeStore;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private String session;
}
