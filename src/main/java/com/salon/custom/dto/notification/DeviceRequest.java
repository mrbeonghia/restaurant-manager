package com.salon.custom.dto.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceRequest {
    private String deviceId;
    private String deviceToken;
    private String deviceType;

}
