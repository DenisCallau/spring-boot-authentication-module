package com.callau.users.service;

import com.callau.users.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private UserService userService;

    @Value("${jwt.expiration}")
        private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        userService.updateLoginData(user);
        Date now = new Date();

        return Jwts.builder()
                .setIssuer("Users Microservice")
                .setSubject(user.getId().toString())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Long.parseLong(this.expiration)))
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public Boolean isTokenValid(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.valueOf(claims.getSubject());
    }

}
