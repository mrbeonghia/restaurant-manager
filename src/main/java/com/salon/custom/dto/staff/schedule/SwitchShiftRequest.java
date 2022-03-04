package com.salon.custom.dto.staff.schedule;

import com.salon.custom.dto.staff.StaffChangeRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SwitchShiftRequest {
    private Long shiftId;
    private StaffChangeRequest staffSwitchRequest;
    private StaffChangeRequest staffReceiveRequest;
}
