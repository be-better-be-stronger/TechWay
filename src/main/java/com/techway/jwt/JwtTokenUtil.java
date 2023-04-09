package com.techway.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.techway.entity.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
	private static final long EXPIRE_DURATION = 60 * 60 * 1000; // The token should expire after 1 hour
    
    @Value("${app.jwt.secret}")
    private String SECRET_KEY;
     
    public String generateAccessToken(Account account) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", account.getId(), account.getEmail()))
                .setIssuer("Techway")
                .claim("roles", account.getRoles().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    
    //verify a given JWT. It returns true if the JWT is verified, or false otherwise
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }
         
        return false;
    }
     
    //Gets the value of the subject field of a given token. 
    //The subject contains User ID and email, which will be used to recreate a User object
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
     
    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
