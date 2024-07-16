package com.foro_med.angel.foro_med.angel.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.foro_med.angel.foro_med.angel.domain.user.User;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;


@Service
public class TokenService {

    @Value("${api.security.secret}") // Asegúrate de tener configurada esta propiedad en tu archivo de propiedades
    private String apiSecret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro_med_angel") // Emisor del token
                    .withSubject(user.getLogin()) // Sujeto del token (nombre de usuario)
                    .withClaim("id", user.getId()) // Otros claims, como el ID del usuario
                    .withExpiresAt(generateExpirationDate()) // Fecha de expiración del token
                    .sign(algorithm); // Firma del token
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el token JWT", e);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new IllegalArgumentException("El token no puede ser nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("foro_med_angel") // Emisor del token
                    .build()
                    .verify(token); // Verificación del token
            return verifier.getSubject(); // Devuelve el sujeto del token (nombre de usuario)
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error al verificar el token JWT", e);
        }
    }
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-5)); // Expira en 2 horas
    }
}