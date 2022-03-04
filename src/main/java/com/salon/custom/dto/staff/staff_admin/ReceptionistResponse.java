package com.salon.custom.dto.staff.staff_admin;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReceptionistResponse extends BaseResponse {
    private ReceptionistDTO staffAdminDTO;
    private List<ReceptionistDTO> staffAdminDTOS;
    private PageDto pageDto;

    public ReceptionistResponse() {
        setSuccess(Boolean.TRUE);
    }

    public ReceptionistResponse(String message, int code) {
        super(message, code);
    }

}
