package com.salon.custom.dto.staff.staff_order;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.combo.combo.ComboColor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StaffOrderResponse extends BaseResponse {
    private StaffOrderDTO staffOrderDTO;
    private List<StaffOrderDTO> staffOrderDTOS;
    private List<ComboColor> comboColors;
    private Long totalMoney;

    public StaffOrderResponse(String message, int code) {
        super(message, code);
    }

}
