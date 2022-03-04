package com.salon.custom.dto.user.user_update_info;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateInfoDTO {
    private Long id;
    private String name;
    private String furiganaName;
    private String phoneNumber;
    private Boolean updatePhone;
}
