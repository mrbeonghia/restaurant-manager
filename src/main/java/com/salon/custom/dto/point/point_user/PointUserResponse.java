package com.salon.custom.dto.point.point_user;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PointUserResponse extends BaseResponse {
    private Long totalSendPointStore;
    private Long totalUsePointStore;
    private PointUserDTO pointRequestDTO;
    private List<PointUserDTO> pointRequestDTOS;
    private PageDto pageDto;

    public PointUserResponse() {
        setSuccess(Boolean.TRUE);
    }

    public PointUserResponse(String message, int code) {
        super(message, code);
    }

}
