package com.salon.custom.dto.order;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
    private Long id;
    private Long bookingId;
    private Long foodId;
    private String foodName;
    private Integer quantity;
    private Date orderTime;
    private String status;
}
