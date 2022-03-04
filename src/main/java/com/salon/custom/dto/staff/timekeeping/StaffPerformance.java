package com.salon.custom.dto.staff.timekeeping;

import com.salon.custom.dto.staff.StaffDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffPerformance {
    private StaffDTO staff;
    private Long staffId;
    private float workingRateInCurrentDay;
    private float workingPerformanceIn30DaysLast;

    public StaffPerformance(StaffDTO staff, float workingPerformanceIn30DaysLast) {
        this.staff = staff;
        this.workingPerformanceIn30DaysLast = workingPerformanceIn30DaysLast;
        this.workingRateInCurrentDay = 0F;
    }
}
