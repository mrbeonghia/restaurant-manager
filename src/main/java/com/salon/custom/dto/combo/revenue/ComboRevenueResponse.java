package com.salon.custom.dto.combo.revenue;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComboRevenueResponse extends BaseResponse {
    private ComboRevenueDTO comboRevenueDTO;
    private List<ComboRevenueDTO> comboRevenueDTOS;

    public ComboRevenueResponse(String message, int code) {
        super(message, code);
    }

    public ComboRevenueResponse(ComboRevenueDTO comboRevenueDTO) {
        this.comboRevenueDTO = comboRevenueDTO;
        this.code = 200;
    }

    public ComboRevenueResponse(List<ComboRevenueDTO> comboRevenueDTOS) {
        this.comboRevenueDTOS = comboRevenueDTOS;
        this.code = 200;
    }
}
