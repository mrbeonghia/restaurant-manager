package com.salon.custom.dto.combo.combo_pack;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComboPackResponse extends BaseResponse {
    private ComboPackDTO comboDTO;
    private List<ComboPackDTO> comboDTOS;
    private PageDto pageDto;

    public ComboPackResponse() {
        setSuccess(Boolean.TRUE);
    }


    public ComboPackResponse(String message, int code) {
        super(message, code);
    }

    public ComboPackResponse(List<ComboPackDTO> comboDTOS) {
        super();
        this.comboDTOS = comboDTOS;
    }

}
