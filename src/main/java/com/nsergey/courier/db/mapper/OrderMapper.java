package com.nsergey.courier.db.mapper;

import com.nsergey.courier.db.model.Order;
import com.nsergey.courier.db.model.OrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    List<Order> findAll();

    Order findById(@Param("orderId") long orderId);

    List<Order> findAllByCourierId(@Param("courierId") long courierId);

    List<Order> findAllByCustomerId(@Param("customerId") long customerId);

    List<Order> findAllByCourierIdAndStatus(@Param("courierId") long courierId,
                                            @Param("orderStatuses") OrderStatus[] orderStatuses);

    void updateState(@Param("orderId") Long orderId, @Param("status") OrderStatus status);
}
