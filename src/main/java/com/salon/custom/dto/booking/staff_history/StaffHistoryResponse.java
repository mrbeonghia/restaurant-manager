package com.salon.custom.dto.booking.staff_history;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffHistoryResponse extends BaseResponse {
    private List<StaffHistoryDTO> staffHistoryDTOS;
    private PageDto pageDto;

    public StaffHistoryResponse(String message, int code) {
        super(message, code);
    }

    public StaffHistoryResponse(List<StaffHistoryDTO> staffHistoryDTOS, PageDto pageDto) {
        this.staffHistoryDTOS = staffHistoryDTOS;
        this.pageDto = pageDto;
        this.code = 200;
    }

    public StaffHistoryResponse(List<StaffHistoryDTO> staffHistoryDTOS) {
        this.staffHistoryDTOS = staffHistoryDTOS;
        this.code = 200;
    }
}
