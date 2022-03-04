package com.salon.custom.dto.staff.staff_performance;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffPerformanceResponse extends BaseResponse {
    private StaffPerformanceDTO staffPerformanceDTO;
    private List<StaffPerformanceDTO> staffPerformanceDTOS;
    private PageDto pageDto;

    public StaffPerformanceResponse(String message, int code) {
        super(message, code);
    }

    public StaffPerformanceResponse(List<StaffPerformanceDTO> staffPerformanceDTOS) {
        this.staffPerformanceDTOS = staffPerformanceDTOS;
        this.code = 200;
    }
}
