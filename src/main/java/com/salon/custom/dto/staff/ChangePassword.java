package com.salon.custom.dto.staff;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {
    private Long staffId;
    private String oldPassword;
    private String newPassword;
}
