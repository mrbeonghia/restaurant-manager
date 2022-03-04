package com.salon.custom.dto.staff.timekeeping;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TimekeepingResponse extends BaseResponse {
    private TimekeepingDTO timekeepingDTO;
    private List<TimekeepingDTO> timekeepingDTOS;

    public TimekeepingResponse(String message, int code) {
        super(message, code);
    }

    public TimekeepingResponse() {
        this.code = 200;
    }

    public TimekeepingResponse(List<TimekeepingDTO> timekeepingDTOS) {
        this.timekeepingDTOS = timekeepingDTOS;
        this.code = 200;
    }
}
