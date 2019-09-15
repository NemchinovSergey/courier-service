package com.nsergey.courier.db.model;

import lombok.Data;

import java.time.Instant;

/**
 * Задание для оператора колл-центра
 */
@Data
public class Task {
    private Long id;
    private Long orderId;
    private Order order;
    private Instant created;
    private Boolean done;
}
