package com.salon.custom.dto.notification.point_notification;

import com.salon.custom.dto.point.PointDTO;
import com.salon.custom.dto.point.point_send.PointSendDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataPoint {
    private PointDTO pointDTO;
    private String type;

}
