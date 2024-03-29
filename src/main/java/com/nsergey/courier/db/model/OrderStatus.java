package com.nsergey.courier.db.model;

import lombok.Getter;

public enum OrderStatus {
    UNKNOWN("Неизвестный"),
    NEW("Новый"),
    IN_PROGRESS("Доставка"),
    CANCELED("Отменён"),
    DELIVERED("Доставлен"),
    SCHEDULING("Согласование времени доставки");

    @Getter
    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }
}
