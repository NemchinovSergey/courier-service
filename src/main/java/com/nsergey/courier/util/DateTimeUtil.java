package com.nsergey.courier.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public abstract class DateTimeUtil {

    // todo вынести формат в настройки
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    // todo таймзона нужно брать в запросе web-клиента
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).withZone(ZoneId.systemDefault());

    public static String toString(Instant instant) {
        return instant == null ? "" : DATE_TIME_FORMATTER.format(instant);
    }

}
