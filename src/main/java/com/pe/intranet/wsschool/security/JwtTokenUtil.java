package com.pe.intranet.wsschool.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//Clase S1
@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret.key}") // Expression Language ${}
    private String secret;

    @Value("${jwt.expiration}") // Expression Language ${}
    private Long JWT_TOKEN_VALIDITY;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.secret-base64}")
    private String secretBase64;

    @Value("${security.jwt.key}")
    private String k1;

    // Agregando data al Payload
    public String generateToken(UserDetails userDetails, List<String> menus) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))); // ADMIN,USER,DBA
        // claims.put("usuario", userDetails.getUsername());
        claims.put("menu", menus);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretBase64.trim()));
        return Jwts.builder()
                .header().type("JWT").keyId(k1).and()
                .issuer(issuer)
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    // utils
    public Claims getAllClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretBase64.trim()));

        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
