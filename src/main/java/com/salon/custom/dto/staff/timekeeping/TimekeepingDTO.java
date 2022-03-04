package com.salon.custom.dto.staff.timekeeping;

import com.salon.custom.dto.staff.staff_order.WorkShiftDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TimekeepingDTO {
    private Long staffId;
    private String staffName;
    private Long storeId;
    private String workday;
    private boolean checkIn;
    private List<WorkShiftDTO> workShiftDTOS;
    private Integer restMinuteDefault;
    private Integer totalRestMinute;
    private Integer restMinuteSurplus;
    private Integer totalWorkMinute;
    private Long salary;
}
