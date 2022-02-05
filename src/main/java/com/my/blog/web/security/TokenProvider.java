package com.my.blog.web.security;

import com.my.blog.web.domain.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@Slf4j
public class TokenProvider {

    @Value("{jwt.key}")
    private String key;


    /*
     * 사용자 정보를 받아서 토큰을 생성한다.
     */
    public String create(UserEntity userEntity)
    {
        // 기한 현재부터 1일 설정
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, key)
                .setSubject(userEntity.getId().toString())
                .setIssuer("blog project")
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .compact();
    }

    public String validateAndGetUserId(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
