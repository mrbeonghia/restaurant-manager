package com.salon.custom.dto.notification;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class NotificationRequest {
    private Long id;
    private String title;
    private String type;
    private String imageUrl;
    private String description;
    private String content;
    private Set<Long> userIds;
    private Set<Long> staffIds;
    private boolean isSendAll;
    private Long storeId;
    private Date startTime;
    private Date endTime;

}
