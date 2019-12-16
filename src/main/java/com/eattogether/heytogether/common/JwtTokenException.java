package com.eattogether.heytogether.common;

public class JwtTokenException extends RuntimeException {
    public JwtTokenException(final RuntimeException e) {
        super(e);
    }
}
