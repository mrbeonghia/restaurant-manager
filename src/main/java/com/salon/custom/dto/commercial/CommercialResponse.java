package com.salon.custom.dto.commercial;

import com.salon.custom.dto.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommercialResponse extends BaseResponse {
    private CommercialDTO commercialDTO;
    private List<CommercialDTO> commercialDTOS;

    public CommercialResponse(String message, int code) {
        super(message, code);
    }

    public CommercialResponse(CommercialDTO commercialDTO) {
        this.commercialDTO = commercialDTO;
        this.code = 200;
    }

    public CommercialResponse(List<CommercialDTO> commercialDTOS) {
        this.commercialDTOS = commercialDTOS;
        this.code = 200;
    }

    public CommercialResponse() {
        this.code = 200;
    }
}
