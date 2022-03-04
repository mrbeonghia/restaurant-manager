package com.salon.custom.dto.booking;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingResponse extends BaseResponse {

    private Long bookingId;

    private BookingDTO calendarOrder;

    private List<BookingDTO> calendarOrders;

    private PageDto pageDto;

    public BookingResponse(String message, int code) {
        super(message, code);
    }

    public BookingResponse(BookingDTO calendarOrder) {
        this.calendarOrder = calendarOrder;
        this.code = 200;
    }

    public BookingResponse(List<BookingDTO> calendarOrders) {
        this.calendarOrders = calendarOrders;
        this.code = 200;
    }

    public BookingResponse(Long bookingId) {
        this.bookingId = bookingId;
        this.code = 200;
    }

    @Override
    public String toString() {
        return "CalendarOrderResponse{" +
                "calendarOrders=" + calendarOrders +
                ", isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

}
