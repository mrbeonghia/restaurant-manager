package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.booking.TableBookingDTO;
import com.salon.custom.entities.TableOfBooking;
import com.salon.custom.repository.TableOfBookingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TableOfBookingService extends BaseService<TableOfBooking, TableOfBookingRepository> {

    public List<TableOfBooking> getByBookingIds(Set<Long> bookingIds){
        return repository.findByBookingIdIn(bookingIds);
    }

    public List<TableOfBooking> getByBookingId(Long bookingId, Set<Long> tableIds){
        return repository.findByBookingId(bookingId, tableIds);
    }

    public List<TableBookingDTO> toDTOS(List<TableOfBooking> tables){
        List<TableBookingDTO> tableBookingDTOS = new ArrayList<>();
        tables.forEach(table -> {
            TableBookingDTO tableBookingDTO = new TableBookingDTO();
            tableBookingDTO.setId(table.getId());
            tableBookingDTO.setBookingId(table.getBooking().getId());
            tableBookingDTO.setName(table.getTableEntity().getName());
            tableBookingDTOS.add(tableBookingDTO);
        });
        return tableBookingDTOS;
    }

    public void saveAll(List<TableOfBooking> tables){
        repository.saveAll(tables);
    }

}
