package com.storeApi.web.security;

import java.util.Date;
import java.util.UUID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTCreator {

	public static final String HEADER_AUTHORIZATION = "Authorization";

    public static String create(String prefix, String key, JWTObject jwtObject) {
        String id = UUID.randomUUID().toString();
        Date now = new Date();
        
        Date exp = new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION);

        String token = Jwts.builder()
                .setId(id)
                .setSubject(jwtObject.getSubject())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return prefix + " " + token;
    }

    public static JWTObject create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        
        JWTObject object = new JWTObject();
        token = token.replace(prefix + " ", ""); 
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
                
        object.setSubject(claims.getSubject());
        object.setExpiration(claims.getExpiration());
        object.setIssuedAt(claims.getIssuedAt());

        return object;
    }
}
