package com.salon.custom.dto.booking.user_booking;

import lombok.Data;

@Data
public class BookingInfoRequest {
    private Long bookingId;
    private Long userId;
    private String customerName;
    private String customerPhone;
    private String customerSex;
    private String commentOfStaff;
    private String customerType;
    private Integer districtId;
    private Integer cityId;
    private String backgroundDisease;
}
