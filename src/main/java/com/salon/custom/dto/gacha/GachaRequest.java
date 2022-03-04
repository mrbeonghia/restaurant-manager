package com.salon.custom.dto.gacha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GachaRequest {
    private Long id;
    private String name;
    private Integer numberOfDigits;
    private Long prizePoint;
    private String imageUrl;

}
