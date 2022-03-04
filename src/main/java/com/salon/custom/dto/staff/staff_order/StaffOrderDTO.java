package com.salon.custom.dto.staff.staff_order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffOrderDTO {
    private Long staffId;
    private String staffName;
    private boolean checkIn;
    private String shiftStatus;
    private List<WorkShiftDTO> workShiftDTOS;

}
