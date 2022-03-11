package com.salon.custom.dto.app_version;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppVersionResponse extends BaseResponse {
    private AppVersionDTO appVersionDTO;
    private PageDto pageDto;

    public AppVersionResponse(String message, int code) {
        super(message, code);
    }

    public AppVersionResponse() {
        setSuccess(Boolean.TRUE);
        this.code = 200;
    }

}
