package com.salon.custom.dto.point.point_send;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointSendResponse extends BaseResponse {
    private PointSendDTO pointSendDTO;

    public PointSendResponse() {
        setSuccess(Boolean.TRUE);
    }

    public PointSendResponse(String message, int code) {
        super(message, code);
    }

}
