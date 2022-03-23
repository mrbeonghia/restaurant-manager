package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.TableOfBooking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TableOfBookingRepository extends BaseRepository<TableOfBooking> {

    @Query(value = "SELECT tb FROM TableOfBooking tb WHERE tb.booking.id IN (?1) " +
            "AND tb.deleted = false ")
    List<TableOfBooking> findByBookingIdIn(Set<Long> bookingIds);

    @Query(value = "SELECT tb FROM TableOfBooking tb WHERE tb.booking.id = ?1 " +
            "AND NOT tb.tableEntity.id IN (?2) AND tb.deleted = false ")
    List<TableOfBooking> findByBookingId(Long bookingId, Set<Long> tableIds);

}
