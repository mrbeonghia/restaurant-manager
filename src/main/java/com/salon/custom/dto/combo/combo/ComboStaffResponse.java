package com.salon.custom.dto.combo.combo;

import com.salon.custom.dto.base.BaseResponse;

import java.util.List;

public class ComboStaffResponse extends BaseResponse {
    private List<ComboStoreDTO> comboStoreDTOS;

    public ComboStaffResponse(String message, int code) {
        super(message, code);
    }

    public ComboStaffResponse(List<ComboStoreDTO> comboStoreDTOS) {
        this.comboStoreDTOS = comboStoreDTOS;
        this.code = 200;
    }
}
