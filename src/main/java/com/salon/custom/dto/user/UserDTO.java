package com.salon.custom.dto.user;

import com.salon.custom.dto.store.StoreDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String userName;
    private String type;
    private String avatarUrl;
    private String phoneNumber;
    private String birthday;
    private String qrCode;
    private String sex;
    private StoreDTO storeDTO;
    private Long storeId;
    private Integer districtId;
    private Integer cityId;
    private String rank;
    private String address;
    private String passwordOwner;
    private String email;
    private Long numberOfPoints;
    private Long customerId;
    private Date createdTime;
    private String furiganaName;
    private String storeName;
    private String gender;
    private Set<Long> staffIdsNG;
    private String customerType;
    private String backgroundDisease;
    private Date registrationDate;


    public UserDTO(Long id, String name, String avatarUrl, String phoneNumber, String sex, String email) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.email = email;
    }

}
