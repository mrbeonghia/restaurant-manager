package com.salon.custom.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPwdRequest {
    private String email;
    private String code;
    private String password;

}
