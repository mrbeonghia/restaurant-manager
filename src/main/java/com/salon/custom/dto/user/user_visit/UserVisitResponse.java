package com.salon.custom.dto.user.user_visit;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserVisitResponse extends BaseResponse {
    private UserVisitDTO userVisitDTO;
    private List<UserVisitDTO> userVisitDTOS;
    private PageDto pageDto;

    public UserVisitResponse() {
        setSuccess(Boolean.TRUE);
    }

    public UserVisitResponse(String message, int code) {
        super(message, code);
    }

}
