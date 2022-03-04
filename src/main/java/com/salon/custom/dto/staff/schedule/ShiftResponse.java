package com.salon.custom.dto.staff.schedule;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShiftResponse extends BaseResponse {
    private ShiftDTO shiftDTO;

    public ShiftResponse(String message, int code) {
        super(message, code);
    }
}
