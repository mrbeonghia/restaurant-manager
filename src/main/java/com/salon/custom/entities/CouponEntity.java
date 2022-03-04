package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coupon")
@Getter
@Setter
public class CouponEntity extends BaseEntity {

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "title")
    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "point")
    private Long point;

}
