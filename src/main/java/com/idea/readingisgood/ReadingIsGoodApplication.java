package com.idea.readingisgood;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootApplication
public class ReadingIsGoodApplication {

    public static void main(String[] args) {
        System.out.println("Bearer token was here: " + getJWTToken());
        SpringApplication.run(ReadingIsGoodApplication.class, args);
    }

    public static String getJWTToken() {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts.builder()
            .setId("JWT")
            .setSubject("rocking")
            .claim("authorities",
                grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
            .compact();

        return "Bearer " + token;
    }
}
