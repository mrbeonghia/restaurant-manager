package com.salon.custom.dto.gacha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrizeDTO {
    private Long id;
    private String randomNumber;
    private String phoneNumber;
    private String name;
    private Integer numberOfDigits;
    private Long prizePoint;
    private String prizeImage;
    private Long pointId;
    private Long pointReview;
    private Long orderId;

}
