package com.nsergey.courier.service;

import com.nsergey.courier.db.mapper.CallCenterTaskMapper;
import com.nsergey.courier.db.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CallCenterService {

    private CallCenterTaskMapper taskMapper;

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
}
