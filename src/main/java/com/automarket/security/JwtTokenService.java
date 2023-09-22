package com.automarket.security;

import com.automarket.exception.ItemNotFoundException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {

    private final String SECRET_KEY;

    public JwtTokenService(@Value("${jwt.secret-key}") String secretKey) {
        SECRET_KEY = secretKey;
    }


    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        Date issuetDate = getIssuetDate();
        Date expiratinDate = getExpirationDate(issuetDate);
        return Jwts
                .builder()
                .addClaims(claims)
                .setSubject(username)
                .setIssuedAt(issuetDate)
                .setExpiration(expiratinDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token){
        Claims claims;
        try{
            claims = Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e){
            String message = "Your jwt is expired, refresh it";
            throw new ItemNotFoundException(message, 40404L);
        }
        return claims;
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        Date currentDate = new Date(System.currentTimeMillis());
        return currentDate
                .after(extractExpiration(token));
    }

    private Date getIssuetDate(){
        return new Date(System.currentTimeMillis());
    }

    private Date getExpirationDate(Date inspiredDate){
        return new Date(inspiredDate.getTime() + SecurityConstants.JWT_LIFE_TIME_60_MIN_IN_MILLIS);
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token)
                .getExpiration();
    }

    public String extractUsername(String token){
        return extractAllClaims(token)
                .getSubject();
    }

    private Key decodeSecretKeyToBase64(String secretKey){
        byte[] keyInBase64 = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyInBase64);
    }

    private Key getSignInKey(){
        return decodeSecretKeyToBase64(SECRET_KEY);
    }


}
