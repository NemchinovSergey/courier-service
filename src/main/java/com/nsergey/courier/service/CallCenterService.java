package com.nsergey.courier.service;

import com.nsergey.courier.db.mapper.CallCenterTaskMapper;
import com.nsergey.courier.db.mapper.OrderMapper;
import com.nsergey.courier.db.model.Order;
import com.nsergey.courier.db.model.OrderStatus;
import com.nsergey.courier.db.model.Task;
import com.nsergey.courier.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class CallCenterService {

    private final CallCenterTaskMapper taskMapper;

    private final OrderMapper orderMapper;

    @Autowired
    public CallCenterService(CallCenterTaskMapper taskMapper, OrderMapper orderMapper) {
        this.taskMapper = taskMapper;
        this.orderMapper = orderMapper;
    }

    /**
     * Добавить задачу в КЦ на перенос времени доставки
     *
     * @param orderId ИД заказа
     */
    private void addOrderRescheduleTask(long orderId) {
        log.info("Add a task to reschedule order, orderId: {}", orderId);
        taskMapper.addOrderRescheduleTask(orderId);
    }

    /**
     * Найти все незавершенные задачи
     */
    public List<Task> findAllUndone() {
        log.info("Find all undone tasks");
        return taskMapper.findAllUndone();
    }

    /**
     * Возвращает задачу по её идентификатору
     *
     * @param taskId ИД задачи
     * @return Task - если найдено, null - если не найдена
     */
    public Task findTaskById(long taskId) {
        log.info("Find a task by id: {}", taskId);
        return taskMapper.findTaskById(taskId);
    }

    /**
     * Добавляет задачу на прозвон клиента для переноса времени доставки заказа
     *
     * @param orderId ИД заказа
     */
    @Transactional
    public void addTaskToRescheduleOrderDelivery(Long orderId) {
        log.info("Add task to reschedule order: {}", orderId);

        Order order = orderMapper.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        addOrderRescheduleTask(order.getId());
        orderMapper.updateState(order.getId(), OrderStatus.SCHEDULING);
    }

    /**
     * Назначает новое время доставки заказа и закрывает задачу в КЦ
     *
     * @param task задача на прозвон клиента
     * @param newDeliveryTime новое время доставки, согласованное с клиентом
     */
    @Transactional
    public void rescheduleOrderByTask(Task task, Instant newDeliveryTime) {
        // todo задать новое время доставки и статус заказа
        // закрыть задачу прозвона
    }

}
