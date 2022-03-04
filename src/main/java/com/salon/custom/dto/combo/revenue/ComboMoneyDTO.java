package com.salon.custom.dto.combo.revenue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComboMoneyDTO {
    private Long comboPackId;
    private Integer minutes;
    private Long moneyFixed;
    private Long moneyTax;
    private Long moneyStaff;
    private Long moneyStore;
}
