package com.nsergey.courier.db.mapper;

import com.nsergey.courier.db.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CallCenterTaskMapper {

    long addOrderRescheduleTask(@Param("orderId") long orderId);

    List<Task> findAllUndone();

    Task findTaskById(@Param("taskId") long taskId);

    void closeTask(@Param("taskId") long taskId);
}
