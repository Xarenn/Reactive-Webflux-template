package com.template.reactive.security.jwt;

import com.template.reactive.security.properties.SecurityProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.template.reactive.security.AuthenticationManager.AUTHORITIES_KEY;

@Component
@AllArgsConstructor
public class JwtUtil {

    private final SecurityProperties serverProperties;

    public String generateToken(String subject, List<String> roles) {
        return createToken(subject, roles);
    }

    private String createToken(String login, List roles) {
        return Jwts.builder()
                .setSubject(login)
                .claim(AUTHORITIES_KEY, roles)
                .signWith(SignatureAlgorithm.HS512, serverProperties.getSigningKey())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + serverProperties.getAccessTokenValidity()*1000))
                .compact();
    }

}
