package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.booking.BookingDTO;
import com.salon.custom.dto.booking.BookingRequest;
import com.salon.custom.dto.booking.BookingResponse;
import com.salon.custom.dto.booking.TableBookingDTO;
import com.salon.custom.dto.order.OrderDTO;
import com.salon.custom.dto.order.OrderRequest;
import com.salon.custom.entities.*;
import com.salon.custom.enums.StatusBooking;
import com.salon.custom.repository.BookingRepository;
import com.salon.custom.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookingService extends BaseService<Booking, BookingRepository> {

    @Autowired
    private TableOfBookingService tableOfBookingService;

    @Autowired
    private TableService tableService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private CouponService couponService;

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
        Map<Long, Coupon> couponMap = couponService.findCouponAvailable().stream()
                .collect(Collectors.toMap(Coupon::getId, Function.identity()));
        Map<Long, List<TableOfBooking>> tableIdToTableOfBookings = new HashMap<>();
        for (TableOfBooking tableOfBooking : tableOfBookingAll) {
            List<TableOfBooking> listInMap = tableIdToTableOfBookings
                    .getOrDefault(tableOfBooking.getTableEntity().getId(), new ArrayList<>());
            listInMap.add(tableOfBooking);
            tableIdToTableOfBookings.put(tableOfBooking.getTableEntity().getId(), listInMap);
        }

        Map<Long, List<TableOfBooking>> bookingIdToTableOfBookings = new HashMap<>();
        for (TableOfBooking tableOfBooking : tableOfBookingAll) {
            List<TableOfBooking> listInMap = bookingIdToTableOfBookings
                    .getOrDefault(tableOfBooking.getBooking().getId(), new ArrayList<>());
            listInMap.add(tableOfBooking);
            bookingIdToTableOfBookings.put(tableOfBooking.getBooking().getId(), listInMap);
        }

        List<TableEntity> tableEntities = tableService.findListTable();
        List<TableBookingDTO> tableBookingDTOS = new ArrayList<>();
        tableEntities.forEach(table -> {
            TableBookingDTO tableBookingDTO = new TableBookingDTO();
            tableBookingDTO.setId(table.getId());
            tableBookingDTO.setName(table.getName());
            tableBookingDTO.setSeat(table.getSeat());
            tableBookingDTO.setAvailable(table.isAvailable());
            List<TableOfBooking> tablesOfBooking = tableIdToTableOfBookings.get(table.getId());
            if (tablesOfBooking != null) {
                List<Booking> booking =
                        tablesOfBooking.stream().map(TableOfBooking::getBooking).collect(Collectors.toList());
                List<BookingDTO> bookingDTOS1 = new ArrayList<>();
                booking.forEach(booking1 -> {
                    BookingDTO bookingDTO = toDTO(booking1);
                    Coupon coupon = couponMap.get(bookingDTO.getCouponId());
                    List<TableOfBooking> tablesOfThisBooking = bookingIdToTableOfBookings.get(booking1.getId());
                    if (tablesOfThisBooking != null){
                        bookingDTO.setTableDTOS(tableOfBookingService.toDTOS(tablesOfThisBooking));
                    }

                    if (coupon != null){
                        bookingDTO.setCouponName(coupon.getTitle());
                    }
                    bookingDTOS1.add(bookingDTO);
                });
                tableBookingDTO.setBookingDTOS(bookingDTOS1);
            }
            tableBookingDTOS.add(tableBookingDTO);
        });
        return new BookingResponse(tableBookingDTOS);
    }


    private BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        UserEntity userEntity = booking.getUserEntity();

        if (userEntity == null) {
            bookingDTO.setCustomerName(booking.getCustomerName());
            bookingDTO.setCustomerPhone(booking.getCustomerPhone());
        } else {
            bookingDTO.setUserId(userEntity.getId());
            bookingDTO.setCustomerName(userEntity.getName());
            bookingDTO.setCustomerPhone(userEntity.getPhoneNumber());
        }
        bookingDTO.setId(booking.getId());
        bookingDTO.setNumberOfCustomers(booking.getNumberOfCustomers());
        bookingDTO.setBookingTime(booking.getBookingTime());
        bookingDTO.setArrivalTime(booking.getArrivalTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setCouponId(bookingDTO.getCouponId());
        return bookingDTO;
    }

    public BookingResponse getBookingDetail(Long bookingId) {
        BookingResponse response = new BookingResponse();
        Booking booking = repository.findByIdAndDeletedFalse(bookingId);
        BookingDTO bookingDTO = toDTO(booking);
        List<OrderDTO> orderDTOS = orderService.getListOrderByBookingId(bookingId);
        bookingDTO.setOrderDTOS(orderDTOS);
        Long bill = 0L;
        if (orderDTOS != null) {
            for (OrderDTO orderDTO : orderDTOS) {
                bill += orderDTO.getTotalPrice();
            }
        }
        bookingDTO.setBill(bill);
        response.setBookingDTO(bookingDTO);
        return response;
    }

    public BookingResponse getHistoryBooking(String startDate, String endDate, Pageable pageable) {
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
            if (tables != null) {
                bookingDTO.setTableDTOS(tableOfBookingService.toDTOS(tables));
            }
            List<OrderDTO> orderDTOS = bookingIdToOrderDTOS.get(booking.getId());
            bookingDTO.setOrderDTOS(orderDTOS);
            Long bill = 0L;
            if (orderDTOS != null) {
                for (OrderDTO orderDTO : orderDTOS) {
                    bill += orderDTO.getTotalPrice();
                }
            }
            bookingDTO.setBill(bill);
            bookingDTOS.add(bookingDTO);
        }
        return new BookingResponse(bookingDTOS, populatePageDto(bookings));

    }

    public BookingResponse createBooking(BookingRequest request) {
        Set<Long> tableIds = request.getTableIds();
        List<TableEntity> tableAvailable = tableService.getTablesAvailable();
        List<TableEntity> tableBookings = tableService.getByIds(request.getTableIds());
        Set<Long> tableAvailableIds = tableAvailable.stream().map(TableEntity::getId).collect(Collectors.toSet());
        if (!tableAvailableIds.containsAll(tableIds)) {
            return new BookingResponse("Some tables are already booked", 4005);
        }
        Booking booking = new Booking();
        booking.setCustomerName(request.getCustomerName());
        String customerPhone = request.getCustomerPhone();
        UserEntity user = userService.getUserByPhone(customerPhone);
        if (user != null) {
            booking.setUserEntity(user);
        } else {
            booking.setCustomerPhone(customerPhone);
        }
        booking.setNumberOfCustomers(request.getNumberOfCustomers());
        booking.setBookingTime(request.getBookingTime());
        booking.setEndTime(DateUtils.getEndTimeBooking(booking.getBookingTime()));
        booking.setStatus(StatusBooking.ORDER.getStatus());
        save(booking);
        List<TableOfBooking> tableOfBookings = new ArrayList<>();
        List<TableEntity> tables = new ArrayList<>();
        for (TableEntity tableEntity : tableBookings) {
            TableOfBooking tableOfBooking = new TableOfBooking();
            tableOfBooking.setBooking(booking);
            tableOfBooking.setTableEntity(tableEntity);
            tableEntity.setAvailable(false);
            tables.add(tableEntity);
            tableOfBookingService.preSave(tableOfBooking);
            tableOfBookings.add(tableOfBooking);
        }
        tableService.saveAll(tables);
        tableOfBookingService.saveAll(tableOfBookings);

//        List<OrderRequest> orderRequests

        return new BookingResponse();
    }

    public BookingResponse updateBooking(BookingRequest request) {
        Set<Long> tableIds = request.getTableIds();
        List<TableEntity> tableAvailable = tableService.getTablesAvailable();
        Set<Long> tableIdsOfThisBooking = tableService.getTableIdsOfBooking(request.getId());
        Set<Long> tableAvailableIds = tableAvailable
                .stream().map(TableEntity::getId).collect(Collectors.toSet());
        tableIds.removeAll(tableIdsOfThisBooking);
        if (!tableAvailableIds.containsAll(tableIds)) {
            return new BookingResponse("Some tables are already booked", 4005);
        }
        Booking booking = repository.findByIdAndDeletedFalse(request.getId());
        if (booking == null) {
            return new BookingResponse("This booking not found", 4005);
        }
        Long bookingId = booking.getId();
        List<TableEntity> tableBookings = tableService.getByIds(request.getTableIds());

        booking.setNumberOfCustomers(request.getNumberOfCustomers());
        booking.setBookingTime(request.getBookingTime());
        booking.setEndTime(DateUtils.getEndTimeBooking(booking.getBookingTime()));
        booking.setStatus(request.getStatus());
        save(booking);
        List<TableOfBooking> tableOfBookings = new ArrayList<>();
        List<TableEntity> tables = new ArrayList<>();
        List<TableOfBooking> tableOfBookingsOld = tableOfBookingService
                .getByBookingId(bookingId, tableIds);
        tableOfBookingsOld.forEach(table -> {
            table.setDeleted(true);
            tableOfBookings.add(table);
        });
        for (TableEntity tableEntity : tableBookings) {
            TableOfBooking tableOfBooking = new TableOfBooking();
            tableOfBooking.setBooking(booking);
            tableOfBooking.setTableEntity(tableEntity);
            tableEntity.setAvailable(false);
            tables.add(tableEntity);
            tableOfBookingService.preSave(tableOfBooking);
            tableOfBookings.add(tableOfBooking);
        }
        tableService.saveAll(tables);
        tableOfBookingService.saveAll(tableOfBookings);

        List<Order> orders = new ArrayList<>();

        List<OrderRequest> orderRequests = request.getOrderRequests();
        Set<Long> orderIds = orderRequests.stream().map(OrderRequest::getId).collect(Collectors.toSet());
        List<Order> ordersOld = orderService.getByBookingIdAndIdNotIn(bookingId,orderIds);
        ordersOld.forEach(order -> {
            order.setDeleted(true);
            orders.add(order);
        });

        List<Order> ordersExist = orderService.getByBookingId(bookingId);
        Map<Long, Order> idToOrderExist = ordersExist.stream()
                .collect(Collectors.toMap((Order::getId), Function.identity()));

        orderRequests.forEach(orderRequest -> {
            Order orderExist = idToOrderExist.get(orderRequest.getId());
            if (orderExist != null){
                orderExist.setFood(foodService.getFoodById(orderRequest.getFoodId()));
                orderExist.setQuantity(orderRequest.getQuantity());
                orderExist.setStatus(orderRequest.getStatus());
                orders.add(orderExist);
            }
            if (orderRequest.getId() == null){
                Order order = new Order();
                order.setBooking(booking);
                order.setFood(foodService.getFoodById(orderRequest.getFoodId()));
                order.setBooking(booking);
                order.setQuantity(orderRequest.getQuantity());
                order.setStatus(orderRequest.getStatus());
                order.setOrderTime(new Date());
                orders.add(order);
            }

        });
        orderService.saveAll(orders);

        return new BookingResponse();
    }

    public BookingResponse actionBooking(BookingRequest request) {
        Booking booking = repository.findByIdAndDeletedFalse(request.getId());
        if (booking == null) {
            return new BookingResponse("This booking not found", 4005);
        }
        booking.setStatus(request.getStatus());
        update(booking);
        return new BookingResponse();
    }

    public BookingResponse actionOrder(BookingRequest request) {
        Booking booking = repository.findByIdAndDeletedFalse(request.getId());
        if (booking == null) {
            return new BookingResponse("This booking not found", 4005);
        }
        List<OrderRequest> orderRequests = request.getOrderRequests();
        for (OrderRequest orderRequest : orderRequests) {

        }
        return new BookingResponse();
    }

}
