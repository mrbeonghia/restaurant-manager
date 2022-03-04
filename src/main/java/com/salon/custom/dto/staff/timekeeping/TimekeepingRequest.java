package com.salon.custom.dto.staff.timekeeping;

import com.salon.custom.dto.staff.StaffChangeRequest;
import com.salon.custom.dto.staff.StaffSignInDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Getter
@Setter
public class TimekeepingRequest {
    private Long scheduleId;
    private Integer restMinuteDefault;
    private Integer restMinuteExtra;
    private String checkInTime;
    private String checkOutTime;
    private StaffChangeRequest staffChangeRequest;
}
