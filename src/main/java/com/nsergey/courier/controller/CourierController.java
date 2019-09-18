package com.nsergey.courier.controller;

import com.nsergey.courier.auth.SecurityContext;
import com.nsergey.courier.db.model.Courier;
import com.nsergey.courier.db.model.Order;
import com.nsergey.courier.service.CallCenterService;
import com.nsergey.courier.service.CourierService;
import com.nsergey.courier.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.nsergey.courier.db.model.OrderStatus.IN_PROGRESS;
import static com.nsergey.courier.db.model.OrderStatus.NEW;

@Slf4j
@Controller
@RequestMapping("/courier")
public class CourierController {

    private final OrderService orderService;

    private final CourierService courierService;
    
    private final CallCenterService callCenterService;

    @Autowired
    public CourierController(OrderService orderService, CourierService courierService, CallCenterService callCenterService) {
        this.orderService = orderService;
        this.courierService = courierService;
        this.callCenterService = callCenterService;
    }

    @GetMapping("/orders")
    public ModelAndView showOrdersList(ZoneId zoneId) {
        Long courierId = SecurityContext.getAuthorisedCourierId();
        Objects.requireNonNull(courierId);

        log.info("Show courier's order list: {}", courierId);

        Courier courier = courierService.findById(courierId);

        List<Order> orders = orderService.findAllByCourierIdAndStatus(courierId, NEW, IN_PROGRESS);
        log.info("Orders count: {}", orders.size());

        List<Order> sortedOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getDeliveryTime))
                .collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView("courier_cabinet");
        modelAndView.addObject("courier", courier);
        modelAndView.addObject("orders", sortedOrders);
        modelAndView.addObject("zoneId", zoneId);
        modelAndView.addObject("title", String.format("Кабинет курьера - %d заказов к доставке", orders.size()));
        return modelAndView;
    }

    @PostMapping(path = "/actions/reschedule", params = "orderId")
    public String rescheduleOrder(@RequestParam("orderId") Long orderId) {
        Long courierId = SecurityContext.getAuthorisedCourierId();
        Objects.requireNonNull(courierId);

        log.info("Add task to reschedule order: {}, courierId: {}", orderId, courierId);
        callCenterService.addTaskToRescheduleOrderDelivery(orderId);

        return "redirect:/courier/orders";
    }

}