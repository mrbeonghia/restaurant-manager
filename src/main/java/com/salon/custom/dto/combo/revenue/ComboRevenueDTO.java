package com.salon.custom.dto.combo.revenue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ComboRevenueDTO {
    private Long comboId;
    private String comboName;
    private Integer minutesBlock;
    private Long revenueFixed;
    private Long revenueStaff;
    private Long revenueStore;
    private Integer tax;
    private List<ComboMoneyDTO> comboMoneyDTOS;

    public ComboRevenueDTO(Long comboId, String comboName, Integer minutesBlock,
                           Long revenueFixed, Long revenueStaff, Long revenueStore, Integer tax) {
        this.comboId = comboId;
        this.comboName = comboName;
        this.minutesBlock = minutesBlock;
        this.revenueFixed = revenueFixed;
        this.revenueStaff = revenueStaff;
        this.revenueStore = revenueStore;
        this.tax = tax;
    }
}
