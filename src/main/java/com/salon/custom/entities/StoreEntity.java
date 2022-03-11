package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import com.salon.custom.enums.StoreCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "store")
@Getter
@Setter
public class StoreEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    /*@Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_map_url")
    private String imageMapUrl;

    @Column(name = "address")
    private String address;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "close_time")
    private String closeTime;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "position")
    private Integer position;

    @Column(name = "is_booking")
    private Boolean isBooking;

    @Column(name = "point_check_in")
    private Long pointCheckIn;

    @Column(name = "point_review")
    private Long pointReview;

    @Column(name = "qr_code_image")
    private String qrCodeImage;

    @Column(name = "qr_code_uuid")
    private String qrCodeUUID;

    @Column(name = "store_url")
    private String storeUrl;

    private Integer bed;*/

    @Column(name = "store_code")
    @Enumerated(EnumType.STRING)
    private StoreCode storeCode;

}
