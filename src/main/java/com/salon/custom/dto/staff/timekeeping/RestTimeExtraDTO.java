package com.salon.custom.dto.staff.timekeeping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RestTimeExtraDTO {
    private Long shiftId;
    private Date startRestTime;
    private Date endRestTime;
    private Integer restMinute;

    public RestTimeExtraDTO(Long shiftId, Date startRestTime, Integer restMinute) {
        this.shiftId = shiftId;
        this.startRestTime = startRestTime;
        this.restMinute = restMinute;
    }
}
