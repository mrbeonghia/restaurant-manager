package com.salon.custom.dto.notification;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PushNotificationRequest {
    private String title;
    private String description;
    private List<PushDeviceRequest> devices;
    private boolean isAll;
    private DataRequest data;
    private Date pushTime;

}
