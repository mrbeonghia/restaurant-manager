package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.booking.BookingDTO;
import com.salon.custom.dto.booking.BookingResponse;
import com.salon.custom.dto.booking.TableBookingDTO;
import com.salon.custom.dto.order.OrderDTO;
import com.salon.custom.dto.table.TableDTO;
import com.salon.custom.entities.Booking;
import com.salon.custom.entities.TableEntity;
import com.salon.custom.entities.TableOfBooking;
import com.salon.custom.repository.BookingRepository;
import com.salon.custom.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService extends BaseService<Booking, BookingRepository> {

    @Autowired
    private TableOfBookingService tableOfBookingService;

    @Autowired
    private TableService tableService;

    @Autowired
    private OrderService orderService;

    /*public BookingResponse getListBooking(String day, Pageable pageable) {
        Date date = DateUtils.convertStringToDateJapan(day);
        Date startTime = DateUtils.getStartTimeOfDay(date);
        Date endTime = DateUtils.getEndTimeOfDay(date);
        Page<Booking> bookings = repository.findByDeletedFalse(startTime, endTime, pageable);
        List<BookingDTO> bookingDTOS = new ArrayList<>();

        Set<Long> bookingIds = bookings.stream().map(Booking::getId).collect(Collectors.toSet());
        List<TableOfBooking> tableOfBookingAll = tableOfBookingService.getByBookingIds(bookingIds);
        Map<Long, List<TableOfBooking>> bookingIdToTables = new HashMap<>();
        for (TableOfBooking tableOfBooking : tableOfBookingAll) {
            List<TableOfBooking> listInMap = bookingIdToTables
                    .getOrDefault(tableOfBooking.getBooking().getId(), new ArrayList<>());
            listInMap.add(tableOfBooking);
            bookingIdToTables.put(tableOfBooking.getBooking().getId(), listInMap);
        }

        bookings.forEach(booking -> {
            BookingDTO bookingDTO = toDTO(booking);
            List<TableOfBooking> tablesOfBooking = bookingIdToTables.get(booking.getId());
            if (tablesOfBooking != null) {
                List<TableEntity> tableEntities = tablesOfBooking.stream().map(TableOfBooking::getTableEntity).collect(Collectors.toList());
                List<TableDTO> tableDTOS = new ArrayList<>();
                tableEntities.forEach(tableEntity -> tableDTOS.add(tableService.toDTO(tableEntity)));
                bookingDTO.setTableDTOS(tableDTOS);
            }
            bookingDTOS.add(bookingDTO);
        });
        return new BookingResponse(bookingDTOS, populatePageDto(bookings));
    }*/


    public BookingResponse getListTableBooking(String day) {
        Date date = DateUtils.convertStringToDateJapan(day);
        Date startTime = DateUtils.getStartTimeOfDay(date);
        Date endTime = DateUtils.getEndTimeOfDay(date);
        List<Booking> bookings = repository.findByDeletedFalse(startTime, endTime);

        Set<Long> bookingIds = bookings.stream().map(Booking::getId).collect(Collectors.toSet());
        List<TableOfBooking> tableOfBookingAll = tableOfBookingService.getByBookingIds(bookingIds);
        Map<Long, List<TableOfBooking>> bookingIdToTables = new HashMap<>();
        for (TableOfBooking tableOfBooking : tableOfBookingAll) {
            List<TableOfBooking> listInMap = bookingIdToTables
                    .getOrDefault(tableOfBooking.getTableEntity().getId(), new ArrayList<>());
            listInMap.add(tableOfBooking);
            bookingIdToTables.put(tableOfBooking.getTableEntity().getId(), listInMap);
        }

        List<TableEntity> tableEntities = tableService.findListTable();
        List<TableBookingDTO> tableBookingDTOS = new ArrayList<>();
        tableEntities.forEach(table -> {
            TableBookingDTO tableBookingDTO = new TableBookingDTO();
            tableBookingDTO.setId(table.getId());
            tableBookingDTO.setName(table.getName());
            tableBookingDTO.setSeat(table.getSeat());
            tableBookingDTO.setAvailable(table.isAvailable());
            List<TableOfBooking> tablesOfBooking = bookingIdToTables.get(table.getId());
            if (tablesOfBooking != null) {
                List<Booking> booking = tablesOfBooking.stream().map(TableOfBooking::getBooking).collect(Collectors.toList());
                List<BookingDTO> bookingDTOS1 = new ArrayList<>();
                booking.forEach(booking1 -> bookingDTOS1.add(toDTO(booking1)));
                tableBookingDTO.setBookingDTOS(bookingDTOS1);
            }
            tableBookingDTOS.add(tableBookingDTO);
        });
        return new BookingResponse(tableBookingDTOS);
    }


    private BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        Long userId = booking.getUserEntity().getId();
        bookingDTO.setUserId(userId);
        if (userId == null) {
            bookingDTO.setCustomerName(booking.getCustomerName());
            bookingDTO.setCustomerPhone(booking.getCustomerPhone());
        } else {
            bookingDTO.setCustomerName(booking.getUserEntity().getName());
            bookingDTO.setCustomerPhone(booking.getUserEntity().getPhoneNumber());
        }
        bookingDTO.setId(booking.getId());
        bookingDTO.setNumberOfCustomers(booking.getNumberOfCustomers());
        bookingDTO.setBookingTime(booking.getBookingTime());
        bookingDTO.setArrivalTime(booking.getArrivalTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setStatus(booking.getStatus());

        return bookingDTO;
    }

    public BookingResponse getBookingDetail(Long bookingId){
        BookingResponse response = new BookingResponse();
        Booking booking = repository.findByIdAndDeletedFalseOrderById(bookingId);
        BookingDTO bookingDTO = toDTO(booking);
        bookingDTO.setOrderDTOS(orderService.getListOrderByBookingId(bookingId));
        response.setBookingDTO(bookingDTO);
        return response;
    }

    public BookingResponse getHistoryBooking(String startDate, String endDate, Pageable pageable){
        Date startTime = DateUtils.getStartTimeOfDay(DateUtils.convertStringToDate(startDate));
        Date endTime = DateUtils.getEndTimeOfDay(DateUtils.convertStringToDate(endDate));
        Page<Booking> bookings = repository.findByDeletedFalse(startTime, endTime, pageable);
        Set<Long> bookingIds = bookings.stream().map(Booking::getId).collect(Collectors.toSet());
        List<TableOfBooking> tableOfBookingAll = tableOfBookingService.getByBookingIds(bookingIds);
        Map<Long, List<TableOfBooking>> bookingIdToTables = new HashMap<>();
        for (TableOfBooking tableOfBooking : tableOfBookingAll) {
            List<TableOfBooking> listInMap = bookingIdToTables
                    .getOrDefault(tableOfBooking.getTableEntity().getId(), new ArrayList<>());
            listInMap.add(tableOfBooking);
            bookingIdToTables.put(tableOfBooking.getTableEntity().getId(), listInMap);
        }
        List<OrderDTO> orderDTOSAll = orderService.getByBookingIds(bookingIds);
        Map<Long, List<OrderDTO>> bookingIdToOrderDTOS = orderDTOSAll
                .stream().collect(Collectors.groupingBy(OrderDTO::getBookingId));
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = toDTO(booking);
            List<TableOfBooking> tables = bookingIdToTables.get(booking.getId());
            List<TableBookingDTO> tableBookingDTOS = new ArrayList<>();
            if (tables != null){
                bookingDTO.setTableDTOS(tableOfBookingService.toDTOS(tables));
            }
            bookingDTO.setOrderDTOS(bookingIdToOrderDTOS.get(booking.getId()));
            bookingDTOS.add(bookingDTO);
        }
        return new BookingResponse(bookingDTOS, populatePageDto(bookings));

    }


}
