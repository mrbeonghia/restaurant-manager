package com.salon.custom.dto.gacha;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrizeResponse extends BaseResponse {
    private PrizeDTO prizeDTO;

    public PrizeResponse() {
        setSuccess(Boolean.TRUE);
    }

    public PrizeResponse(String message, int code) {
        super(message, code);
    }

}
