package com.salon.custom.dto.staff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.salon.custom.dto.LoginResult;
import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class StaffResponse extends BaseResponse {
    private StaffDTO staffDTO;
    private List<StaffDTO> staffs;
    private PageDto pageDto;
    private LoginResult loginResult;

    public StaffResponse() {
        this.code = 200;
    }

    public StaffResponse(StaffDTO staffDTO){
        this.staffDTO = staffDTO;
        this.code = 200;
    }

    public StaffResponse(List<StaffDTO> staffs) {
        this.staffs = staffs;
    }

    public StaffResponse(String message, int code) {
        super(message, code);
    }

    public StaffResponse(List<StaffDTO> staffs, PageDto pageDto) {
        this.staffs = staffs;
        this.pageDto = pageDto;
        this.code = 200;
    }
}
