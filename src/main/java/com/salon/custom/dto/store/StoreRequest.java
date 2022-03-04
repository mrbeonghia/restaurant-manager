package com.salon.custom.dto.store;

import com.salon.custom.enums.StoreCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreRequest {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String description;
    private List<String> images;
    private String imageMapUrl;
    private String address;
    private String openTime;
    private String closeTime;
    private String longitude;
    private String latitude;
    private String nameOwner;
    private String password;
    private String storeUrl;
    private Long pointCheckIn;
    private Long pointReview;
    private Integer bed;
    private StoreCode storeCode;
}
