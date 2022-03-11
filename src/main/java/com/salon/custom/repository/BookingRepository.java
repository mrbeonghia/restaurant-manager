package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookingRepository extends BaseRepository<Booking> {

    @Query(value = "SELECT b FROM Booking b WHERE b.arrivalTime > ?1 AND b.arrivalTime < ?1 " +
            "AND b.deleted = false ")
    Page<Booking> findByDeletedFalse(Date startTIme, Date endTIme, Pageable page);
}
