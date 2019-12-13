package com.eattogether.heytogether.web.config;

import com.eattogether.heytogether.common.JwtTokenException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = LoginInterceptor.class)
public class LoginInterceptorExceptionHandler {

    @ExceptionHandler({JwtTokenException.class})
    public ResponseEntity<Object> handleLoginInterceptorException() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/temp");
        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }
}
