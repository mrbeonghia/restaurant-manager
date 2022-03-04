package com.salon.custom.dto.user;

import com.salon.custom.dto.notification.DeviceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInDto {
    private String userName;
    private String email;
    private String password;
    private DeviceRequest deviceRequest;

}
