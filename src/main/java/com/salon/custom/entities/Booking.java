package com.salon.custom.entities;

import com.salon.base.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "booking")
@Getter
@Setter
public class Booking extends BaseEntity {

    @ManyToOne(targetEntity = TableEntity.class)
    private TableEntity tableEntity;

    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity userEntity;

    private String customerName;

    private String customerPhone;

    private Integer numberOfCustomers;

    private Date bookingTime;

    private Date arrivalTime;

    private String status;

    @ManyToOne(targetEntity = Coupon.class)
    private Coupon coupon;

}
