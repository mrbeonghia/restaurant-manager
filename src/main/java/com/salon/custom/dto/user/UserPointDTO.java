package com.salon.custom.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPointDTO {
    private String name;
    private Long userId;
    private Long userPoint;
    private int gachaTurn;
}
