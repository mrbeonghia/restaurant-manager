package com.salon.custom.dto.notification.point_notification;

import com.salon.custom.dto.user.UserDTO;
import com.salon.custom.entities.StoreEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class NotificationPointDTO {
    private Long id;
    private String type;
    private String title;
    private String actionPoint;
    private Date time;
    private boolean all;
    private UserDTO user;
    private Long storeId;

}
