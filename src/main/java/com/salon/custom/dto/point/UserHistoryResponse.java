package com.salon.custom.dto.point;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserHistoryResponse extends BaseResponse {
    private Long totalSendPointStore;
    private Long totalUsePointStore;
    private UserHistoryDTO userHistoryDTO;
    private List<UserHistoryDTO> userHistoryDTOS;
    private PageDto pageDto;

    public UserHistoryResponse() {
        setSuccess(Boolean.TRUE);
    }

    public UserHistoryResponse(String message, int code) {
        super(message, code);
    }

}
