package com.salon.custom.dto.gacha;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GachaDTO {
    private Long id;
    private String name;
    private Integer numberOfDigits;
    private Long prizePoint;
    private String imageUrl;

}
