package com.salon.custom.dto.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationSeenDTO {
    private Long userId;
    private Long notSeen;

}
