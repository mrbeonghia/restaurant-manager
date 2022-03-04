package com.salon.custom.dto.combo.combo_order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComboOrderFavouriteDTO {
    private Long orderId;
    private Long comboId;
    private String comboName;
    private Long minutes;
    private Long priceDefault;
    private Long priceMemberApp;
    private Long priceNonApp;
}
