package com.health_eat.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JWTUtil {
    private static String SECRET_KEY = "healthEat";
    private static Long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60 * 1000L;
    private static Long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 1000L;

    public static String  createToken(Map<String, Object> obj){
        String jwtString = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .setClaims(obj)
                .signWith(SignatureAlgorithm.HS512 , SECRET_KEY.getBytes())
                .compact();

        return jwtString;
    }

    public static String  createRefreshToken(Map<String, Object> obj){
        String jwtString = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .setClaims(obj)
                .signWith(SignatureAlgorithm.HS512 , SECRET_KEY.getBytes())
                .compact();

        return jwtString;
    }



    public static Boolean checkExpired(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();

        JwsHeader header = Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getHeader();
        Date tokenDate = claims.getExpiration();
        Boolean isExpiration = new Date().before(tokenDate);

        return isExpiration;
    }

    public static String getFild(String token,String fild){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
                return claims.get(fild).toString();
    }

    public static Claims getClaims(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
