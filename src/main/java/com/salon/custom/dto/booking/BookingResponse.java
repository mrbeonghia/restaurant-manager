package com.salon.custom.dto.booking;

import com.salon.custom.dto.base.BaseResponse;
import com.salon.custom.dto.base.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingResponse extends BaseResponse {
    private List<BookingDTO> bookingDTOS;
    private PageDto pageDto;

    public BookingResponse(List<BookingDTO> bookingDTOS, PageDto pageDto) {
        this.bookingDTOS = bookingDTOS;
        this.pageDto = pageDto;
    }

    public BookingResponse(String message, int code) {
        super(message, code);
    }
}
