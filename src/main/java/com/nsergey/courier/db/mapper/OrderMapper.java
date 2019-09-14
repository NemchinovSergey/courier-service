package com.nsergey.courier.db.mapper;

import com.nsergey.courier.db.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    List<Order> findAll();

    Order findById(@Param("orderId") long orderId);

    List<Order> findAllByCourierId(@Param("courierId") long courierId);
}
