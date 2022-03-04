package com.salon.custom.dto.staff.schedule;

import com.salon.custom.dto.staff.StaffChangeRequest;
import lombok.Data;

import java.util.List;

@Data
public class ShiftStaffRequest {
    private Long staffId;
    private String workingDay;
    private List<ShiftRequest> shiftRequests;
    private StaffChangeRequest staffChangeRequest;
}
