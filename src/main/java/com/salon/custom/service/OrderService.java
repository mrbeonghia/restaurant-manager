package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.order.OrderDTO;
import com.salon.custom.dto.order.OrderResponse;
import com.salon.custom.entities.Food;
import com.salon.custom.entities.Order;
import com.salon.custom.repository.OrderRepository;
import com.salon.custom.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class OrderService extends BaseService<Order, OrderRepository> {

    @Autowired
    BookingService bookingService;

    public List<OrderDTO> getListOrderByBookingId(Long bookingId) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<Order> orders = repository.findByBookingIdAndDeletedFalse(bookingId);
        orders.forEach(order -> orderDTOS.add(toDTO(order)));
        return orderDTOS;
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setBookingId(order.getBooking().getId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setOrderTime(order.getOrderTime());
        orderDTO.setStatus(order.getStatus());
        Food food = order.getFood();
        if (food != null) {
            orderDTO.setFoodId(food.getId());
            orderDTO.setFoodName(food.getName());
            orderDTO.setPrice(food.getPrice());
            orderDTO.setImageUrl(food.getImageUrl());
            orderDTO.setTotalPrice(food.getPrice() * order.getQuantity());
        }

        return orderDTO;
    }

    public List<OrderDTO> getByBookingIds(Set<Long> bookingIds) {
        List<Order> orders = repository.findByBookingIdInAndDeletedFalse(bookingIds);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> orderDTOS.add(toDTO(order)));
        return orderDTOS;
    }

    public void saveAll(List<Order> orders) {
        repository.saveAll(orders);
    }

    public List<Order> getByBookingIdAndIdNotIn(Long bookingId, Set<Long> ids){
        return repository.findByBookingIdAndIdNotIn(bookingId, ids);
    }

    public List<Order> getByBookingId(Long bookingId) {
        return repository.findByBookingIdAndDeletedFalse(bookingId);
    }

    public OrderResponse getListOrderInDay(){
        Date startTime = DateUtils.getStartTimeOfDay(new Date());
        Date endTime = DateUtils.getEndTimeOfDay(new Date());
        List<Order> orders = repository.findOrderInDay();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> orderDTOS.add(toDTO(order)));
        return new OrderResponse(orderDTOS);
    }


}
