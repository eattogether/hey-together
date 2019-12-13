package com.eattogether.heytogether.controller;

import com.eattogether.heytogether.common.JwtUtil;
import com.eattogether.heytogether.controller.dto.LoginDto;
import com.eattogether.heytogether.controller.dto.UserDto;
import com.eattogether.heytogether.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.eattogether.heytogether.common.ControllerCookieHelper.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        UserDto userDto = userService.loginUser(loginDto);

        String token = JwtUtil.createToken(userDto);
        Cookie rememberMe = createDefaultCookie(JWT_COOKIE_NAME, token);
        response.addCookie(rememberMe);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request, JWT_COOKIE_NAME);

        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok("logout");
    }

}
