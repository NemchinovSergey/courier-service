package com.nsergey.courier.db.model;

public enum OrderStatus {
    UNKNOWN("Неизвестный"),
    NEW("Новый"),
    IN_PROGRESS("Доставка"),
    CANCELED("Отменён"),
    DELIVERED("Доставлен");

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }
}
