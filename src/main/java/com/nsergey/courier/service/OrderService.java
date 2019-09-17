package com.nsergey.courier.service;

import com.nsergey.courier.db.mapper.OrderMapper;
import com.nsergey.courier.db.model.Order;
import com.nsergey.courier.db.model.OrderStatus;
import com.nsergey.courier.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    private final CallCenterService callCenterService;

    @Autowired
    public OrderService(OrderMapper orderMapper, CallCenterService callCenterService) {
        this.orderMapper = orderMapper;
        this.callCenterService = callCenterService;
    }

    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    public List<Order> findAllByCourierId(Long courierId) {
        return orderMapper.findAllByCourierId(courierId);
    }

    public List<Order> findAllByCourierIdAndStatus(Long courierId, OrderStatus... orderStatuses) {
        return orderMapper.findAllByCourierIdAndStatus(courierId, orderStatuses);
    }

    @Transactional
    public void addTaskToRescheduleOrderDelivery(Long orderId) {
        log.info("Reschedule order: {}", orderId);
        Order order = orderMapper.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        callCenterService.addOrderRescheduleTask(order.getId());
        orderMapper.updateState(order.getId(), OrderStatus.SCHEDULING);
    }

}
