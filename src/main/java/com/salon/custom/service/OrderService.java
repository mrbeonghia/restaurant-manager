package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.order.OrderDTO;
import com.salon.custom.dto.order.OrderRequest;
import com.salon.custom.dto.order.OrderResponse;
import com.salon.custom.entities.Order;
import com.salon.custom.repository.OrderRepository;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderService extends BaseService<Order, OrderRepository> {

    @Autowired BookingService bookingService;

    public List<OrderDTO> getListOrderByBookingId(Long bookingId){
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<Order> orders = repository.findByBookingIdAndDeletedFalse(bookingId);
        orders.forEach(order -> orderDTOS.add(toDTO(order)));
        return orderDTOS;
    }

    private OrderDTO toDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setBookingId(order.getBooking().getId());
        orderDTO.setFoodId(order.getFood().getId());
        orderDTO.setFoodName(order.getFood().getName());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setOrderTime(order.getOrderTime());
        orderDTO.setStatus(order.getStatus());
        return orderDTO;
    }

    public List<OrderDTO> getByBookingIds(Set<Long> bookingIds){
        List<Order> orders = repository.findByBookingIdInAndDeletedFalse(bookingIds);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> orderDTOS.add(toDTO(order)));
        return orderDTOS;
    }

    public void saveAll(List<Order> orders){
        repository.saveAll(orders);
    }


}
