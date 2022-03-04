package com.salon.custom.dto.not_good;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotGoodResponse extends BaseResponse {
    private NotGoodDTO notGoodDTO;
    private List<NotGoodDTO> notGoodDTOS;
    private PageDto pageDto;

    public NotGoodResponse(NotGoodDTO notGoodDTO) {
        this.notGoodDTO = notGoodDTO;
        this.code = 200;
    }

    public NotGoodResponse(List<NotGoodDTO> notGoodDTOS, PageDto pageDto) {
        this.notGoodDTOS = notGoodDTOS;
        this.code = 200;
        this.pageDto = pageDto;
    }

    public NotGoodResponse(String message, int code) {
        super(message, code);
    }

}
