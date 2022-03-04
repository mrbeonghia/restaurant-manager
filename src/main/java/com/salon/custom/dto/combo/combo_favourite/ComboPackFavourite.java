package com.salon.custom.dto.combo.combo_favourite;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ComboPackFavourite {
    private Set<Long> ids;
    private Long comboId;
    private Integer minutes;
    private Long priceDefault;
    private Long priceMemberApp;
    private Long priceNonApp;
    private String color;
    private Long point;
}
