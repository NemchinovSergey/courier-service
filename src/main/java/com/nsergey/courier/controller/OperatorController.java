package com.nsergey.courier.controller;

import com.nsergey.courier.auth.SecurityContext;
import com.nsergey.courier.db.model.Order;
import com.nsergey.courier.db.model.Task;
import com.nsergey.courier.service.CallCenterService;
import com.nsergey.courier.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Controller
@RequestMapping("/operator")
public class OperatorController {

    private final OrderService orderService;

    private final CallCenterService callCenterService;

    @Autowired
    public OperatorController(OrderService orderService, CallCenterService callCenterService) {
        this.orderService = orderService;
        this.callCenterService = callCenterService;
    }

    @GetMapping(value = "/tasks")
    public ModelAndView showTasks(ZoneId zoneId) {
        Long operatorId = SecurityContext.getAuthorisedOperatorId();
        Objects.requireNonNull(operatorId);

        log.info("Show operator's order list: {}", operatorId);

        List<Task> tasks = callCenterService.findAllUndone();

        List<Task> sortedTasks = tasks.stream()
                .sorted(Comparator.comparing(Task::getCreated))
                .collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView("operator_cabinet");
        modelAndView.addObject("tasks", sortedTasks);
        modelAndView.addObject("zoneId", zoneId);
        modelAndView.addObject("title", String.format("Кабинет оператора - %d заданий на согласование", tasks.size()));
        return modelAndView;
    }

    @PostMapping(path = "/actions/schedule", params = "taskId")
    public String scheduleOrder(@RequestParam("taskId") Long taskId) {
        Long operatorId = SecurityContext.getAuthorisedOperatorId();
        Objects.requireNonNull(operatorId);

        log.info("Schedule taskId: {}", taskId);
        Task task = callCenterService.findTaskById(taskId);
        if (!task.getDone()) {
            // тут конечно должно быть то время, которое укажет оператор
            // однако я просто прибавляют 1 день
            Instant newDeliveryTime = getNewDeliveryTime(task);
            callCenterService.rescheduleOrderByTask(task, newDeliveryTime);
        }

        return "redirect:/operator/tasks";
    }

    /**
     * Упрощенная версия получения нового времени доставки заказа
     *
     * @param task задача на прозвон
     * @return новое время доставки
     */
    private Instant getNewDeliveryTime(Task task) {
        Order order = orderService.findById(task.getOrderId());
        return order.getDeliveryTime().plus(1, DAYS);
    }

}
