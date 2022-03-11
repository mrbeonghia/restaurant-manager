package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.entities.TableOfBooking;
import com.salon.custom.repository.TableOfBookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TableOfBookingService extends BaseService<TableOfBooking, TableOfBookingRepository> {

    public List<TableOfBooking> getByBookingIds(Set<Long> bookingIds){
        return repository.findByBookingIdIn(bookingIds);
    }

}
