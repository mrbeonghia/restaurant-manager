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
    private BookingDTO bookingDTO;
    private List<BookingDTO> bookingDTOS;
    private List<TableBookingDTO> tableBookingDTOS;
    private PageDto pageDto;

    public BookingResponse(List<BookingDTO> bookingDTOS, PageDto pageDto) {
        this.bookingDTOS = bookingDTOS;
        this.pageDto = pageDto;
    }

    public BookingResponse(String message, int code) {
        super(message, code);
    }

    public BookingResponse(List<TableBookingDTO> tableBookingDTOS) {
        this.tableBookingDTOS = tableBookingDTOS;
    }


}
