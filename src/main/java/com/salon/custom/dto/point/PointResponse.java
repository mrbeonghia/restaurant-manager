package com.salon.custom.dto.point;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import com.salon.custom.dto.notification.point_notification.NotificationPointResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PointResponse extends BaseResponse {
    private PointDTO pointDTO;
    private List<PointDTO> pointDTOList;
    private PageDto pageDto;

    public PointResponse() {
        setSuccess(Boolean.TRUE);
    }

    public PointResponse(String message, int code) {
        super(message, code);
    }

}
