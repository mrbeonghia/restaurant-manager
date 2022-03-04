package com.salon.custom.dto.notification.point_notification;

import com.salon.custom.dto.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotificationPointRequest {
    private Long id;
    private String type;
    private String title;
    private String actionPoint;
    private Date time;
    private boolean all;
    private Long userId;
    private Long storeId;

}
