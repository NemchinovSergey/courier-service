package com.nsergey.courier.service;

import com.nsergey.courier.db.mapper.CallCenterTaskMapper;
import com.nsergey.courier.db.model.Order;
import com.nsergey.courier.db.model.Task;
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

    @Autowired
    public CallCenterService(CallCenterTaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    /**
     * Добавить задачу в КЦ на перенос времени доставки
     *
     * @param orderId ИД заказа
     */
    public void addOrderRescheduleTask(long orderId) {
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
