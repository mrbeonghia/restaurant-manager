package com.salon.custom.dto.staff;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffChangeRequest {
    private String phone;
    private String password;
    private String type;
}
