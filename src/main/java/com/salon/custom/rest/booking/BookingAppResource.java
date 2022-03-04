/*
package com.salon.custom.rest.booking;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.booking.BookingResponse;
import com.salon.custom.dto.booking.BookingsResponse;
import com.salon.custom.dto.booking.order_history.BookingHistoryResponse;
import com.salon.custom.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BookingAppResource extends BaseResource<BookingService> {

    @GetMapping("api/v2/app/detail-booking")
    public ResponseEntity<BookingResponse> getBookingDetailForApp(@RequestParam(name = "id") Long id) {
        BookingResponse bookingResponse = service.getDetailBooking(id);

        return ResponseEntity.ok().body(bookingResponse);
    }

    @GetMapping("api/v2/app/bookings")
    public ResponseEntity<BookingsResponse> getBookingsDetail(@RequestParam String qrCodeUUID) {
        BookingsResponse response = service.getBookingsDetail(qrCodeUUID);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/v2/app/bookings-can-be-checkin")
    public ResponseEntity<BookingsResponse> getBookingsCanBeCheckin(@RequestParam String qrCodeUUID) {
        BookingsResponse response = service.getBookingsCanBeCheckin(qrCodeUUID);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("api/v2/app/booking")
    public ResponseEntity<BookingResponse> deleteBookingForApp(@RequestParam(name = "orderId") Long orderId) {
        BookingResponse bookingResponse = service.deleteBookingForApp(orderId);

        return ResponseEntity.ok().body(bookingResponse);
    }

    @PutMapping("api/v2/app/cancel-booking")
    public ResponseEntity<BookingResponse> cancelBookingByApp(@RequestParam(name = "orderId") Long orderId) {
        BookingResponse bookingResponse = service.cancelBookingForApp(orderId);

        return ResponseEntity.ok().body(bookingResponse);
    }

    @GetMapping("api/v2/app/booking")
    public ResponseEntity<BookingHistoryResponse> getBookingByUserApp(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        BookingHistoryResponse calendarOrderResponse = service.getBookingHistoryByUserApp(pageable);

        return ResponseEntity.ok().body(calendarOrderResponse);
    }

    @GetMapping("api/app/v2/getBookingByQr")
    public ResponseEntity<BookingResponse> getBookingAccompanyByQrCode(@RequestParam String qrCode) {
        return ResponseEntity.ok().body(service.getBookingAccompanyByQrCode(qrCode));
    }

    @PutMapping("api/app/v2/receiveBooking")
    public ResponseEntity<BookingResponse> receiveBookingAccompany(@RequestParam Long id) {
        return ResponseEntity.ok().body(service.receiveBookingAccompany(id));
    }

}
*/
