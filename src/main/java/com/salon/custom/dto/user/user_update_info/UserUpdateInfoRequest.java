package com.salon.custom.dto.user.user_update_info;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateInfoRequest {
    private Long id;
    private String furiganaName;
    private String phoneNumber;
}
