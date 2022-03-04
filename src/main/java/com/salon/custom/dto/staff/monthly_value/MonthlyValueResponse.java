package com.salon.custom.dto.staff.monthly_value;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyValueResponse extends BaseResponse {
    private MonthlyValueDTO monthlyValueDTO;

    public MonthlyValueResponse(MonthlyValueDTO monthlyValueDTO) {
        this.monthlyValueDTO = monthlyValueDTO;
        this.code = 200;
    }

    public MonthlyValueResponse(String message, int code) {
        super(message, code);
    }
}
