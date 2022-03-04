package com.salon.custom.dto.staff.schedule;

import com.salon.custom.dto.staff.StaffChangeRequest;
import com.salon.custom.dto.staff.StaffSignInDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffScheduleRequest {
    private Long staffId;
    private Integer dayOfWeek;
    private List<ShiftRequest> shiftRequests;
    private StaffChangeRequest staffChangeRequest;
}
