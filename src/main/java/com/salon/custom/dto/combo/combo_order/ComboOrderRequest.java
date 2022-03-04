package com.salon.custom.dto.combo.combo_order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComboOrderRequest {
    private Long comboId;
    private Long comboPackId;
    private Integer minutes;
    private Long price;
}
