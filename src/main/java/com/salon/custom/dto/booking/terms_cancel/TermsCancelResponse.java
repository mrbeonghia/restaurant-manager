package com.salon.custom.dto.booking.terms_cancel;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.entities.TermsCancel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TermsCancelResponse extends BaseResponse {
    private TermsCancelDTO termsCancelDTO;

    public TermsCancelResponse(TermsCancelDTO termsCancelDTO) {
        this.termsCancelDTO = termsCancelDTO;
        this.code = 200;
    }
}
