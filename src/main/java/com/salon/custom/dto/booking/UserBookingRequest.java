package com.salon.custom.dto.booking;

import com.salon.custom.dto.booking.request.BookingRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserBookingRequest {
    private Long storeId;
    private Date startTime;
    private List<BookingRequest> bookingRequests;

    public void selfPopulate() {
        bookingRequests.forEach(bookingRequest -> bookingRequest.selfPopulate(startTime, storeId));
    }
}
