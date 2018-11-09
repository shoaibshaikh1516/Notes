package com.clairvoyant.notes.security;

import com.clairvoyant.notes.model.CustomNotesUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private Long jwtExpirationInS;

    @Value("${app.jwt.header.prefix}")
    private String tokenRequestHeaderPrefix;

    private String encodedSecret;

    @PostConstruct
    protected void init() {
        this.encodedSecret = encodeSecret(this.jwtSecret);
    }

    protected String encodeSecret(String plainSecret) {
        if (StringUtils.isEmpty(plainSecret)) {
            throw new IllegalArgumentException("JWT secret cannot be null or empty.");
        }
        return Base64.getEncoder().encodeToString(this.jwtSecret.getBytes());
    }

    protected Date getExpirationTime() {
        Date now = new Date();
        Long expireInMilis = TimeUnit.SECONDS.toMillis(jwtExpirationInS);
        return new Date(expireInMilis + now.getTime());
    }

    public String generateToken(CustomNotesUserDetails customNotesUserDetails) {
        Date now = new Date();
        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(customNotesUserDetails.getUsername())
                .setIssuedAt(now).setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS512, this.encodedSecret).compact();
    }


    public UsernamePasswordAuthenticationToken getUser(String token) {
        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey(this.encodedSecret)
                    .parseClaimsJws(token.replace(tokenRequestHeaderPrefix, "")).getBody();
            String userName = claims.getSubject();
            if (userName != null) {
                return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
            }
        }
        return null;
    }

    public Long getExpiryDuration() {
        return jwtExpirationInS;
    }

}
