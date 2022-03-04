package com.salon.custom.dto.booking;

import com.salon.custom.dto.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookingsResponse extends BaseResponse {
    private List<BookingDTO> bookings;
}
