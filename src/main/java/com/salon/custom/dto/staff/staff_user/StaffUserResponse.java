package com.salon.custom.dto.staff.staff_user;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class StaffUserResponse extends BaseResponse {
    private StaffUserDTO staffUserDTO;
    private Map<Integer, Set<Long>> userAndStaffIds;
    private List<StaffUserDTO> staffUserDTOS;
    private List<StaffSexDTO> staffSexDTOS;
    private PageDto pageDto;

    public StaffUserResponse(String message, int code) {
        super(message, code);
    }

    public StaffUserResponse(StaffUserDTO staffUserDTO) {
        this.code = 200;
        this.staffUserDTO = staffUserDTO;
    }

    public StaffUserResponse() {
        this.code = 200;
    }

    public StaffUserResponse(List<StaffSexDTO> staffSexDTOS) {
        this.staffSexDTOS = staffSexDTOS;
        this.code = 200;
    }

    public StaffUserResponse(List<StaffUserDTO> staffUserDTOS, PageDto pageDto) {
        this.staffUserDTOS = staffUserDTOS;
        this.pageDto = pageDto;
        this.code = 200;
    }

    public StaffUserResponse(Map<Integer, Set<Long>> userAndStaffIds, List<StaffSexDTO> staffSexDTOS) {
        this.userAndStaffIds = userAndStaffIds;
        this.staffSexDTOS = staffSexDTOS;
    }
}
