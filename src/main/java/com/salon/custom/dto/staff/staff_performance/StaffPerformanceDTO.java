package com.salon.custom.dto.staff.staff_performance;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StaffPerformanceDTO {
    private Long id;
    private Long staffId;
    private Long storeId;
    private Date workingDate;
    private Float workPerformance;

}
