package com.salon.custom.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private Long id;
    private String email;
    private String name;
    private String userName;
    private String phoneNumber;
    private String birthday;
    private String sex;
    private Integer cityId;
    private Integer districtId;
    private String address;
    private Long storeId;
    private String password;
    private String otp;
    private String code;
    private String avatarUrl;
    private String furiganaName;
    private String backgroundDisease;
    private String customerType;
}
