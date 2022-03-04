package com.salon.custom.dto.staff.schedule;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ScheduleOverlap {
    private Long startTime;
    private Long endTime;
    private int countOverlap;

    public ScheduleOverlap() {
        this.countOverlap = 1;
    }
}
