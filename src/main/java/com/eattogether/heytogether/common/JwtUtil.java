package com.eattogether.heytogether.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eattogether.heytogether.controller.dto.UserDto;
import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Map;

public class JwtUtil {

    private static final String JWT_SECRET_KEY = "time";

    public static String createToken(UserDto userDto) {
        LocalDateTime nowTime = LocalDateTime.now();
        Algorithm algorithm = Algorithm.HMAC256(nowTime.toString());

        return JWT.create()
                .withExpiresAt(Date.valueOf(nowTime.toLocalDate().plusDays(1L)))
                .withIssuer(userDto.getUserId())
                .withClaim(JWT_SECRET_KEY, nowTime.toString())
                .sign(algorithm);
    }

    public static void validate(String token) {
        String payload = new String(Base64.decodeBase64URLSafe(JWT.decode(token).getPayload()));
        Gson gson = new Gson();
        Map map = gson.fromJson(payload, Map.class);

        String time = map.get(JWT_SECRET_KEY).toString();
        Algorithm algorithm1 = Algorithm.HMAC256(time);
        DecodedJWT decode = JWT.decode(token);
        algorithm1.verify(decode);
    }
}
