package com.salon.custom.dto.user;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPointResponse extends BaseResponse {
    private UserPointDTO userPointDTO;

    public UserPointResponse(String message, int code) {
        super(message, code);
    }

    public UserPointResponse(UserPointDTO userPointDTO) {
        this.userPointDTO = userPointDTO;
        this.code = 200;
    }
}
