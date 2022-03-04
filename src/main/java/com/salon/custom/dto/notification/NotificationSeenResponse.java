package com.salon.custom.dto.notification;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationSeenResponse extends BaseResponse {
    private NotificationSeenDTO notificationSeenDTO;
    private List<NotificationSeenDTO> notificationSeenDTOS;
    private PageDto pageDto;

    public NotificationSeenResponse() {
        setSuccess(Boolean.TRUE);
    }

    public NotificationSeenResponse(String message, int code) {
        super(message, code);
    }

}
