package com.salon.custom.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffBookingDto {
    private Long staffId;
    private String staffName;
    private Long bookingId;
    private Date start;
    private Date finishWorking;
    private Date time;
}
