package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
@Getter
@Setter
public class StaffEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "sex")
    private String sex;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birthday")
    private String birthday;

    /*@Column(name = "month_day")
    private String monthDay;

    @Column(name = "tue_day")
    private String tueDay;

    @Column(name = "wed_day")
    private String wedDay;

    @Column(name = "thu_day")
    private String thuDay;

    @Column(name = "fri_day")
    private String friDay;

    @Column(name = "sat_day")
    private String satDay;

    @Column(name = "sun_day")
    private String sunDay;*/

    @Column(name = "rating_average")
    private Float ratingAverage;

    @Column(name = "skill")
    private String skill;

    @Column(name = "self_introduction")
    private String selfIntroduction;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "password_encode")
    private String passwordEncode;

}
