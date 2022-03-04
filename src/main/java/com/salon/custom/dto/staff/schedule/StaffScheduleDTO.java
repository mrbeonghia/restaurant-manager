package com.salon.custom.dto.staff.schedule;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffScheduleDTO {
    private Long staffId;
    private String staffName;
    private List<DayOfWeekDTO> dayOfWeekDTOS;
}
