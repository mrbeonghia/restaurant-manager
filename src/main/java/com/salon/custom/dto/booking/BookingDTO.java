package com.salon.custom.dto.booking;

import com.salon.custom.dto.order.OrderDTO;
import com.salon.custom.dto.table.TableDTO;
import com.salon.custom.entities.Coupon;
import com.salon.custom.entities.TableEntity;
import com.salon.custom.entities.UserEntity;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class BookingDTO {
    private Long id;
    private List<TableBookingDTO> tableDTOS;
    private Long userId;
    private String customerName;
    private String customerPhone;
    private Integer numberOfCustomers;
    private Date bookingTime;
    private Date arrivalTime;
    private Date endTime;
    private String status;
    private Long couponId;
    private String couponName;
    private List<OrderDTO> orderDTOS;
    private Long bill;
}
