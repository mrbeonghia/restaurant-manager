package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
public class UserEntity extends BaseEntity implements Serializable {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "type")
    private String type;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "sex")
    private String sex;

    @Column(name = "password")
    private String password;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "address")
    private String address;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "number_of_points")
    private Long numberOfPoints;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "furigana_name")
    private String furiganaName;

    @Column(name = "background_disease")
    private String backgroundDisease;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
