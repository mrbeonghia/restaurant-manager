package com.salon.custom.dto.user.user_update_info;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateInfoResponse extends BaseResponse {
    private UserUpdateInfoDTO userUpdateInfoDTO;

    public UserUpdateInfoResponse() {
        setSuccess(Boolean.TRUE);
    }

    public UserUpdateInfoResponse(String message, int code) {
        super(message, code);
    }

}
