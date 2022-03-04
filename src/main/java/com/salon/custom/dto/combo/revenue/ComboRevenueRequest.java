package com.salon.custom.dto.combo.revenue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComboRevenueRequest {
    private Long comboId;
    private Integer minutesBlock;
    private Long revenueFixed;
    private Long revenueStaff;
    private Long revenueStore;
    private Integer tax;
}
