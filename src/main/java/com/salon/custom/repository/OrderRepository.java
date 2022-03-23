package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends BaseRepository<Order> {

    List<Order> findByBookingIdAndDeletedFalse(Long bookingId);

    List<Order> findByBookingIdInAndDeletedFalse(Set<Long> bookingId);

    @Query(value = "SELECT o FROM Order o WHERE o.booking.id = ?1 " +
            "AND NOT o.id IN (?2) AND o.deleted = false ")
    List<Order> findByBookingIdAndIdNotIn(Long bookingId, Set<Long> ids);

}
