package com.nsergey.courier.service;

import com.nsergey.courier.db.mapper.OrderMapper;
import com.nsergey.courier.db.model.Order;
import com.nsergey.courier.db.model.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    public List<Order> findAllByCourierId(Long courierId) {
        return orderMapper.findAllByCourierId(courierId);
    }

    public Order findById(Long orderId) {
        return orderMapper.findById(orderId);
    }

    public List<Order> findAllByCourierIdAndStatus(Long courierId, OrderStatus... orderStatuses) {
        return orderMapper.findAllByCourierIdAndStatus(courierId, orderStatuses);
    }

}
