package com.salon.custom.dto.staff.staff_user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StaffSexDTO {
    private String sex;
    private List<StaffUserDTO> staffUserDTOS;
}
