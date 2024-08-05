package com.Meshal.PMS.service;

import com.Meshal.PMS.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.*;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;
import static java.time.Instant.now;

@Service
public class JwtService {

    private final static String secretKey =
            "bm2G7a26U2VR+Xt1hRg+beZ46H3XMMJQE1Z8q77vtMOv9b7tyZz9fylFKYohsHuECwRCW0pN6yOdOUXTVcKXcywJp4aj+l1yd8kYvmssEIc=";

    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
    public static String genToken(User user) {
        return Jwts.builder()
                .claim("email",user.getEmail())
                .claim("id", user.getUserId())
                .claim("firstName",user.getFirstName())
                .claim("lastName",user.getLastName())
                .claim("dob",user.getDateOfBirth())
                .setSubject(user.getEmail())
                .setIssuedAt(Date.from(now()))
                .setExpiration(Date.from(now().plus(1l, ChronoUnit.HOURS)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token, User user) {
        final String userEmail = extractEmail(token);
        return (user.getEmail().equals(userEmail)) && (!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
    return extractTokenExpiration(token).before(new Date());
    }

    private Date extractTokenExpiration(String token) {
       return extractClaims(token, Claims::getExpiration);
    }

    public void disableToken(String token){
        Claims claims = getAllClaimsFromToken(token);
        claims.remove("id");
    }
}
