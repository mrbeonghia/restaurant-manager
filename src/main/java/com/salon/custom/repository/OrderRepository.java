package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order> {

    List<Order> findByBookingIdAndDeletedFalse(Long bookingId);

}
