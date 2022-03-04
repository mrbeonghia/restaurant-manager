package com.salon.custom.dto.staff.schedule;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftRequest {
    private Long id;
    private String startTime;
    private String endTime;
    private String session;
    private String type;
}
