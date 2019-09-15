package com.nsergey.courier.controller;

import com.nsergey.courier.auth.SecurityContext;
import com.nsergey.courier.db.model.Order;
import com.nsergey.courier.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.nsergey.courier.db.model.OrderStatus.IN_PROGRESS;
import static com.nsergey.courier.db.model.OrderStatus.NEW;

@Slf4j
@Controller
@RequestMapping("/courier")
public class CourierController {

    private final OrderService orderService;

    @Autowired
    public CourierController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ModelAndView showOrdersList() {
        log.info("Show courier's order list: {}", SecurityContext.getAuthorisedCourierId());
        ModelAndView modelAndView = new ModelAndView("courier_cabinet");

        List<Order> orders = orderService.findAllByCourierIdAndStatus(SecurityContext.getAuthorisedCourierId(), NEW, IN_PROGRESS);
        log.info("Orders count: {}", orders.size());

        List<Order> sortedOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getDeliveryTime))
                .collect(Collectors.toList());

        modelAndView.addObject("orders", sortedOrders);
        modelAndView.addObject("title", String.format("Кабинет курьера - %d заказов к доставке", orders.size()));

        return modelAndView;
    }

}