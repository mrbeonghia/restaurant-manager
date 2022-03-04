package com.salon.custom.dto.combo.combo;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComboResponse extends BaseResponse {
    private ComboDTO comboDTO;
    private List<ComboDTO> comboDTOS;
    private PageDto pageDto;

    public ComboResponse() {
        setSuccess(Boolean.TRUE);
        setCode(200);
    }


    public ComboResponse(String message, int code) {
        super(message, code);
    }

    public ComboResponse(List<ComboDTO> comboDTOS) {
        this.comboDTOS = comboDTOS;
        this.code = 200;
    }
}
