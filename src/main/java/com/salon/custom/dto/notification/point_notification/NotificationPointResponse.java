package com.salon.custom.dto.notification.point_notification;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationPointResponse extends BaseResponse {
    private NotificationPointDTO notificationPointDTO;
    private List<NotificationPointDTO> notificationPointDTOS;
    private PageDto pageDto;

    public NotificationPointResponse() {
        setSuccess(Boolean.TRUE);
    }

    public NotificationPointResponse(String message, int code) {
        super(message, code);
    }

}
