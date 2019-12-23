package com.eattogether.heytogether.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eattogether.heytogether.service.dto.UserDto;
import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class JwtUtil {

    public static final String JWT_TOKEN_HEADER = "Authorization";
    private static final String HEADER_SPLITTER = " ";
    private static final String JWT_SECRET_KEY = "time";
    private static final String EXPIRE = "expire";
    private static final long EXPIRE_DATE = 3L;

    public static String createToken(UserDto userDto) {
        LocalDateTime nowTime = LocalDateTime.now();
        Algorithm algorithm = Algorithm.HMAC256(nowTime.toString());

        return JWT.create()
                .withIssuer(userDto.getUserId())
                .withClaim(EXPIRE, nowTime.plusDays(EXPIRE_DATE).toString())
                .withClaim(JWT_SECRET_KEY, nowTime.toString())
                .sign(algorithm);
    }

    public static void validate(String token) throws JwtTokenException {
        Map<String, String> map = parsePayload(token);
        String expireTime = map.get(EXPIRE);
        String secretKey = map.get(JWT_SECRET_KEY);
        try {
            validateExpire(expireTime);
            validateSignature(secretKey, token);
        } catch (RuntimeException e) {
            throw new JwtTokenException(e);
        }
    }

    public static String getClaimValue(String token, String key) {
        return parsePayload(token).get(key);
    }

    public static String getJwtTokenFrom(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(JWT_TOKEN_HEADER);
        if (Objects.isNull(header)) {
            throw new JwtTokenException("토큰이 없습니다.");
        }

        return header.split(HEADER_SPLITTER)[1];
    }

    private static Map<String, String> parsePayload(final String token) {
        String payload = new String(Base64.decodeBase64URLSafe(JWT.decode(token).getPayload()));
        Gson gson = new Gson();

        return gson.fromJson(payload, Map.class);
    }

    private static void validateExpire(final String time) {
        LocalDateTime expireTime = LocalDateTime.parse(time);

        if (expireTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateSignature(final String secretKey, final String token) {
        Algorithm jwtAlgorithm = Algorithm.HMAC256(secretKey);
        DecodedJWT decode = JWT.decode(token);
        jwtAlgorithm.verify(decode);
    }
}
