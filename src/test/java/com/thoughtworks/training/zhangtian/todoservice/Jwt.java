package com.thoughtworks.training.zhangtian.todoservice;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Jwt {

    @Test
    public void testJwt() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("name", "zhang");
        claims.put("role", "dev");

        byte[] security = "kitty".getBytes();

        String jwtBuilder = Jwts.builder()
                .addClaims(claims)
//                .setExpiration(Date.from(Instant.now().minus(1, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS512, "kitty".getBytes())
                .compact();

        Claims body = Jwts.parser()
                .setSigningKey(security)
                .parseClaimsJws(jwtBuilder)
                .getBody();

        assertThat(body.get("name"), is("zhang"));
        assertThat(body.get("role"), is("dev"));
        System.out.println(jwtBuilder);
    }
}