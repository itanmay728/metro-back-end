package com.metro.metrobackend.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long expirationMs = 1000 * 60 * 60; // 1 hour

	public String generateToken(String email, Set<String> roles) {
		return Jwts.builder().setSubject(email).claim("roles", roles).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationMs)).signWith(key).compact();
	}

	public String getEmail(String token) {
		return parseToken(token).getBody().getSubject();
	}

	public boolean validate(String token) {
		try {
			parseToken(token);
			return true;
		} catch (JwtException ex) {
			return false;
		}
	}

	private Jws<Claims> parseToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	}
}
