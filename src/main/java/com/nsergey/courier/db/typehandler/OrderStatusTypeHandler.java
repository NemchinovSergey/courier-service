package com.nsergey.courier.db.typehandler;

import com.nsergey.courier.db.model.OrderStatus;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(OrderStatus.class)
public class OrderStatusTypeHandler extends EnumOrdinalTypeHandler<OrderStatus> {
    public OrderStatusTypeHandler(Class<OrderStatus> type) {
        super(type);
    }
}
