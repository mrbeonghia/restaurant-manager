package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.TableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TableRepository extends BaseRepository<TableEntity> {

    Page<TableEntity> findByDeletedFalse(Pageable page);

    List<TableEntity> findByDeletedFalse();

    TableEntity findByIdAndDeletedFalse(Long id);

    TableEntity findByNameAndDeletedFalse(String name);

    List<TableEntity> findByIdInAndDeletedFalse(Set<Long> ids);

    @Query(value = "SELECT t FROM TableEntity t " +
            "WHERE t.available = true AND t.deleted = false ")
    List<TableEntity> findTablesAvailable();

    @Query(value = "SELECT t.id FROM TableEntity t LEFT JOIN TableOfBooking tb ON t = tb.tableEntity " +
            "WHERE tb.booking.id = ?1 AND t.deleted = false ")
    Set<Long> findTableIdsOfBooking(Long bookingId);

    @Query(value = "SELECT t FROM TableEntity t LEFT JOIN TableOfBooking tb ON t = tb.tableEntity " +
            "WHERE tb.booking.id = ?1 AND t.deleted = false ")
    List<TableEntity> findTableOfBooking(Long bookingId);

}
