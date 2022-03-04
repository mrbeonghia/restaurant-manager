package com.salon.custom.dto.staff.schedule;

import lombok.Data;

import java.util.Date;

@Data
public class ShiftExistDTO {
    private Long staffId;
    private Long storeId;
    private Long scheduleId;
}
