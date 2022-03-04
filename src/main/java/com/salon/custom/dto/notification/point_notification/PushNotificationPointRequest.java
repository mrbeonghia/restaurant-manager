package com.salon.custom.dto.notification.point_notification;

import com.salon.custom.dto.notification.PushDeviceRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PushNotificationPointRequest {
    private String title;
    private String description;
    private String content;
    private List<PushDeviceRequest> devices;
    private boolean isAll;
    private DataPoint data;

}
