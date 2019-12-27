package com.eattogether.heytogether.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

public class ControllerCookieHelper {

    public static final String JWT_COOKIE_NAME = "remember_me";
    private static final int DEFAULT_COOKIE_EXPIRY_TIME = 7 * 24 * 60 * 60;

    public static Cookie getCookie(final HttpServletRequest request, final String cookieName) {
        validateCookies(request);

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst()
                .orElseThrow(CookieNotFoundException::new);
    }

    private static void validateCookies(final HttpServletRequest request) {
        if (Objects.isNull(request.getCookies())) {
            throw new CookieNotFoundException();
        }
    }

    public static Cookie createDefaultCookie(final String name, final String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(DEFAULT_COOKIE_EXPIRY_TIME);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        return cookie;
    }
}
