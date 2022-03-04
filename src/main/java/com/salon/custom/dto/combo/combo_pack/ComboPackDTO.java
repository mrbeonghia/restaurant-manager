package com.salon.custom.dto.combo.combo_pack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComboPackDTO {
    private Long id;
    private Long comboId;
    private String comboName;
    private Integer minutes;
    private Long priceDefault;
    private Long priceMemberApp;
    private Long priceNonApp;
    private String color;
    private Long point;
    private Boolean applyCoupon;
    private Long staffWage;
    private Long tax;

    public ComboPackDTO(Long id, Long comboId, String comboName, Integer minutes) {
        this.id = id;
        this.comboId = comboId;
        this.comboName = comboName;
        this.minutes = minutes;
    }

    public ComboPackDTO(Long id, Long comboId, String comboName, Integer minutes, Long priceDefault,
                        Long priceMemberApp, Long priceNonApp, String color, Long point, Boolean applyCoupon) {
        this.id = id;
        this.comboId = comboId;
        this.comboName = comboName;
        this.minutes = minutes;
        this.priceDefault = priceDefault;
        this.priceMemberApp = priceMemberApp;
        this.priceNonApp = priceNonApp;
        this.color = color;
        this.point = point;
        this.applyCoupon = applyCoupon;
    }
}

