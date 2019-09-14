package com.nsergey.courier.db.model;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long addressId;
    private Long customerId;
    private OrderStatus state;
    private Long courierId;
    private String notes;
}
