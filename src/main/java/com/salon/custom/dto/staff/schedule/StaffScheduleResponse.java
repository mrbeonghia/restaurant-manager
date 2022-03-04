package com.salon.custom.dto.staff.schedule;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffScheduleResponse extends BaseResponse {
    private StaffScheduleDTO scheduleDTO;
    private List<StaffScheduleDTO> scheduleDTOS;
    private PageDto pageDto;

    public StaffScheduleResponse(String message, int code) {
        super(message, code);
    }

    public StaffScheduleResponse(StaffScheduleDTO scheduleDTO) {
        this.scheduleDTO = scheduleDTO;
        this.code = 200;
    }

    public StaffScheduleResponse(List<StaffScheduleDTO> scheduleDTOS) {
        this.scheduleDTOS = scheduleDTOS;
        this.code = 200;
    }

    public StaffScheduleResponse() {
        this.code = 200;
    }
}
