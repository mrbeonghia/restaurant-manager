package com.salon.custom.dto.booking;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableBookingDTO {
    private Long id;
    private List<BookingDTO> bookingDTOS;
    private String name;
    private Integer seat;
    private Boolean available;

}
