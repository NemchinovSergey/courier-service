package com.nsergey.courier.auth;

/**
 * Эмуляция контекста авторизации
 */
public abstract class SecurityContext {

    private static final long COURIER_ID = 1L;
    private static final long OPERATOR_ID = 1001L;

    public static Long getAuthorisedCourierId() {
        return COURIER_ID;
    }

    public static Long getAuthorisedOperatorId() {
        return OPERATOR_ID;
    }

}
