package com.nsergey.courier.auth;

public abstract class SecurityContext {

    private static final long COURIER_ID = 1L;

    public static Long getAuthorisedCourierId() {
        return COURIER_ID;
    }

}
