package com.spcrey.utils;

import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtil {
    
    private static final String KRY = "spcrey";

    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
            .withClaim("claims", claims)
            .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
            .sign(Algorithm.HMAC256(KRY));
    }

    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KRY))
            .build()
            .verify(token)
            .getClaim("claims")
            .asMap();
    }
}
