package com.salon.custom.dto.staff;

import com.salon.custom.dto.notification.DeviceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffSignInDTO {
    private String phone;
    private String password;
    private DeviceRequest deviceRequest;

}
