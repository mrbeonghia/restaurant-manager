package com.salon.custom.dto.staff.staff_user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffUserRequest {
    private Long id;
    private String name;
    private String phoneNumber;
    private String sex;
    private String oldPassword;
    private String newPassword;

}
