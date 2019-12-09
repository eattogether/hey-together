package com.eattogether.heytogether.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eattogether.heytogether.controller.dto.LoginDto;
import com.eattogether.heytogether.controller.dto.UserDto;
import com.eattogether.heytogether.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        UserDto userDto = userService.loginUser(loginDto);
        String token = createToken(userDto);
        Cookie rememberMe = createRememberCookie(token);

        response.addCookie(rememberMe);

        logger.debug("remember_me cookie: {}", token);

        return userDto;
    }

    private Cookie createRememberCookie(String token) {
        Cookie rememberMe = new Cookie("remember_me", token);
        rememberMe.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        rememberMe.setHttpOnly(true);
        rememberMe.setPath("/");
        return rememberMe;
    }

    private String createToken(UserDto userDto) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create()
                .withIssuedAt(Date.valueOf(LocalDate.now()))
                .withExpiresAt(Date.valueOf(LocalDate.of(2019, 12, 20)))
                .withIssuer(userDto.getUserId())
                .sign(algorithm);
    }
}
