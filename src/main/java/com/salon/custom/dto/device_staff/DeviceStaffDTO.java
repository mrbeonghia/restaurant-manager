package com.salon.custom.dto.device_staff;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class DeviceStaffDTO {
    private String deviceId;

    private String deviceToken;

    private String type;
}
