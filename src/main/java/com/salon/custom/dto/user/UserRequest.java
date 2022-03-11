package com.salon.custom.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String birthday;
    private String gender;
}
