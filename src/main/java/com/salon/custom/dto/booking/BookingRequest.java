package com.salon.custom.dto.booking;

import com.salon.custom.dto.order.OrderDTO;
import com.salon.custom.dto.order.OrderRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BookingRequest {
    private Long id;
    private Set<Long> tableIds;
    private Long userId;
    private String customerName;
    private String customerPhone;
    private Integer numberOfCustomers;
    private Date bookingTime;
    private Date arrivalTime;
    private Date endTime;
    private String status;
    private Long couponId;
    private List<OrderRequest> orderRequests;
}
