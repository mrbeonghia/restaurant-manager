package com.salon.custom.dto.booking;

import com.salon.custom.dto.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffBookingResponse extends BaseResponse {
    private List<StaffBookingDto> staffBookings;
}
