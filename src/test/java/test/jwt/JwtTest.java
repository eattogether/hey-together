package test.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;

public class JwtTest {
    private static final Logger logger = LoggerFactory.getLogger(JwtTest.class);

    @Test
    void jwt_use() {
        //HMAC
        Algorithm algorithm = Algorithm.HMAC256("secret");

        String token = JWT.create()
                .withIssuedAt(Date.valueOf(LocalDate.now()))
                .withExpiresAt(Date.valueOf(LocalDate.of(2019, 12, 9)))
                .withIssuer("auth0")
                .sign(algorithm);

        logger.debug("jwt_token: {}", token);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);

        logger.debug("decoded_issuer: {}", jwt.getIssuer());
        logger.debug("decoded_issuer: {}", jwt.getExpiresAt());
        logger.debug("decoded_issuer: {}", jwt.getClaim("userId").asString());
    }
}
