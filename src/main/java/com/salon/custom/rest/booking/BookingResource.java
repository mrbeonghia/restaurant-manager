package com.salon.custom.rest.booking;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.booking.BookingResponse;
import com.salon.custom.dto.categoty.CategoryResponse;
import com.salon.custom.service.BookingService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingResource extends BaseResource<BookingService> {

    @GetMapping("api/getTableBooking")
    public ResponseEntity<BookingResponse> getCategory(@RequestParam("day") String day) {
        BookingResponse response = service.getListTableBooking(day);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/getBookingDetail")
    public ResponseEntity<BookingResponse> getBookingDetail(@RequestParam("id") Long id) {
        BookingResponse response = service.getBookingDetail(id);
        return ResponseEntity.ok().body(response);
    }

}
