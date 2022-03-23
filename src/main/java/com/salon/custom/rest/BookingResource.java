package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.booking.BookingRequest;
import com.salon.custom.dto.booking.BookingResponse;
import com.salon.custom.dto.categoty.CategoryResponse;
import com.salon.custom.service.BookingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("api/getHistoryBooking")
    public ResponseEntity<BookingResponse> getHistoryBooking(@RequestParam("startDate") String startDate,
                                                             @RequestParam("endDate") String endDate,
                                                             @RequestParam(name = "page", defaultValue = "1") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        BookingResponse response = service.getHistoryBooking(startDate, endDate, pageable);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("api/createBooking")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse response = service.createBooking(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("api/updateBooking")
    public ResponseEntity<BookingResponse> updateBooking(@RequestBody BookingRequest request) {
        BookingResponse response = service.updateBooking(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("api/actionBooking")
    public ResponseEntity<BookingResponse> actionBooking(@RequestBody BookingRequest request) {
        BookingResponse response = service.actionBooking(request);
        return ResponseEntity.ok().body(response);
    }

}
