package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.entities.Booking;
import com.salon.custom.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService extends BaseService<Booking, BookingRepository> {
}
