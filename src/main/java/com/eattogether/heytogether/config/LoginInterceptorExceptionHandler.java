package com.eattogether.heytogether.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = LoginInterceptorExceptionHandler.class)
public class LoginInterceptorExceptionHandler {

    @ExceptionHandler({SignatureVerificationException.class, NullPointerException.class, JWTDecodeException.class})
    public ResponseEntity<Object> temp() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/");
        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }
}
