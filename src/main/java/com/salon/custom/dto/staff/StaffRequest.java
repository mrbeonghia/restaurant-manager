package com.salon.custom.dto.staff;

import com.salon.custom.entities.RoleEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffRequest {
    private Long id;
    private String name;
    private String gender;
    private String avatarUrl;
    private String phoneNumber;
    private String birthday;
    private String email;
    private String password;
    private Boolean isActive;
    private String role;

}
