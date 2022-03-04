package com.salon.custom.dto.combo.combo_order;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComboOrderDTO {
    private Long orderId;
    private Long comboId;
    private String comboName;
    private boolean favourite;
    private Long comboPackId;
    private Integer minutes;
    private String color;
    private Long point;
    private Long priceDefault;
    private Long priceMemberApp;
    private Long priceNonApp;
    @ApiModelProperty(hidden = true)
    private Integer minutesBlock;
    @ApiModelProperty(hidden = true)
    private Long revenueStaff;
    private boolean prolonged;

    public ComboOrderDTO(Long orderId, Long comboId, String comboName, Long comboPackId, Integer minutes) {
        this.orderId = orderId;
        this.comboId = comboId;
        this.comboName = comboName;
        this.comboPackId = comboPackId;
        this.minutes = minutes;
    }

}
