package com.salon.custom.dto.notification;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationResponse extends BaseResponse {
    private NotificationDTO notification;
    private List<NotificationDTO> notifications;
    private PageDto pageDto;

    public NotificationResponse() {
        setSuccess(Boolean.TRUE);
    }

    public NotificationResponse(String message, int code) {
        super(message, code);
    }

    public NotificationResponse(NotificationDTO notification) {
        this.notification = notification;
        this.code = 200;
    }
}
