package com.salon.custom.dto.staff.schedule;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DayOfWeekDTO {
    private String dayOfWeek;
    private String day;
    private List<ShiftDTO> shiftDTOS;
}
