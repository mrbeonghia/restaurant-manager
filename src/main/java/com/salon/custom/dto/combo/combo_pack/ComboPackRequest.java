package com.salon.custom.dto.combo.combo_pack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComboPackRequest {
    private Long id;
    private Integer minutes;
    private Long priceDefault;
    private Long priceMemberApp;
    private Long priceNonApp;
    private String color;
    private Long point;
    private Boolean applyCoupon;
    private Long staffWage;
    private Long tax;
}
