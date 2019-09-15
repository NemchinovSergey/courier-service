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

    public void addOrderRescheduleTask(long orderId) {
        log.info("Add task to reschedule order, orderId: {}", orderId);
        taskMapper.addOrderRescheduleTask(orderId);
    }

    public List<Task> findAllTasks() {
        return taskMapper.findAll();
    }

    public Task findTaskById(long taskId) {
        return taskMapper.findTaskById(taskId);
    }
}
