package com.eattogether.heytogether.common;

public class CookieNotFoundException extends RuntimeException {

    public CookieNotFoundException() {
    }

    public CookieNotFoundException(final Throwable cause) {
        super(cause);
    }
}
