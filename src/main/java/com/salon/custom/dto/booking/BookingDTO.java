package com.salon.custom.dto.booking;

import com.salon.custom.entities.Coupon;
import com.salon.custom.entities.TableEntity;
import com.salon.custom.entities.UserEntity;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Set;

@Data
public class BookingDTO {
    private Set<Long> tableId;
    private Set<String> tableName;
    private Long userId;
    private String customerName;
    private String customerPhone;
    private Integer numberOfCustomers;
    private Date bookingTime;
    private Date arrivalTime;
    private String status;
    private Long couponId;
    private String couponName;
}
