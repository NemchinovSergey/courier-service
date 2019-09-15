package com.nsergey.courier.db.model;

import lombok.Data;

import java.time.Instant;

@Data
public class Order {
    /**
     * ИД заказа
     */
    private Long id;
    /* ИД адреса доставки */
    private Long addressId;
    /**
     * Адрес доставки
     */
    private String address;
    /**
     * ИД заказчика
     */
    private Long customerId;
    /**
     * Название заказчика
     */
    private String customerName;
    /**
     * Время доставки
     */
    private Instant deliveryTime;
    /**
     * ИД курьера
     */
    private Long courierId;
    /**
     * Состояние заказа
     */
    private OrderStatus state;
}
