package com.nsergey.courier.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneId.systemDefault;

public abstract class DateTimeUtil {

    // todo вынести формат в настройки
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    /**
     * Форматирует дату/время Instant в строку
     */
    public static String toString(Instant instant) {
        return instant == null ? "" : DATE_TIME_FORMATTER.withZone(systemDefault()).format(instant);
    }

    public static String toString(Instant instant, ZoneId zoneId) {
        return instant == null ? "" : DATE_TIME_FORMATTER.withZone(zoneId).format(instant);
    }

}
