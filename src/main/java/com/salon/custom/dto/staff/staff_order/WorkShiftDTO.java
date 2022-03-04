package com.salon.custom.dto.staff.staff_order;

import com.salon.custom.dto.booking.BookingDTO;
import com.salon.custom.dto.staff.timekeeping.RestTimeExtraDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class WorkShiftDTO {
    private Long scheduleId;
    private Date startTime;
    private Date endTime;
    private Date checkInTime;
    private Date checkOutTime;
    private String session;
    private Integer restMinuteDefault;
    private Integer totalRestMinuteExtra;
    private Integer totalMinuteExchange;
    private boolean onLeave;
    private Integer customerServed;
    private Integer customerSpecified;
    List<BookingDTO> bookingDTOS;
    List<RestTimeExtraDTO> restTimeExtraDTOS;
}
