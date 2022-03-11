package com.salon.custom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {
    private Long userId;
    private String accessToken;
    private String refreshToken;

}
