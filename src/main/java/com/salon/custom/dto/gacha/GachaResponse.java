package com.salon.custom.dto.gacha;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GachaResponse extends BaseResponse {
    private GachaDTO gachaDTO;
    private List<GachaDTO> gachaDTOList;
    private PageDto pageDto;

    public GachaResponse() {
        setSuccess(Boolean.TRUE);
        setCode(200);
    }

    public GachaResponse(String message, int code) {
        super(message, code);
    }

}
