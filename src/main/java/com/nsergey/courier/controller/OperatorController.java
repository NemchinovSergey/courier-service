package com.nsergey.courier.controller;

import com.nsergey.courier.db.model.Order;
import com.nsergey.courier.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class OperatorController {

    private final OrderService orderService;

    @Autowired
    public OperatorController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/operator")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("operator_cabinet");

        List<Order> orders = orderService.findAll();
        log.info("Orders count: {}", orders.size());

        modelAndView.addObject("orders", orders);

        modelAndView.addObject("title", "Operator cabinet");
        return modelAndView;
    }

}
