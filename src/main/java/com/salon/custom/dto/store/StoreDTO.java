package com.salon.custom.dto.store;

import com.salon.custom.dto.user.UserDTO;
import com.salon.custom.enums.StoreCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StoreDTO {

    private Long id;
    private String name;
    private String phoneNumber;
    private String description;
    private String imageMapUrl;
    private List<ImageStore> images;
    private String address;
    private String openTime;
    private String closeTime;
    private String longitude;
    private String latitude;
    private UserDTO userOwner;
    private Integer position;
    private Boolean booking;
    private String qrCodeImage;
    private String qrCodeUUID;
    private String storeUrl;
    private Long pointCheckIn;
    private Long pointReview;
    private Integer bed;
    private StoreCode storeCode;


    public StoreDTO(Long id, String name, String openTime, String closeTime) {
        this.id = id;
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public StoreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
