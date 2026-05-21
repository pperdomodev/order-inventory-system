package com.inventory.order.test.infrastructure.adapters.output.security;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.inventory.order.test.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	private Key getSigningKey() {

		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(User user) {

		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("role", user.getRole().name())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractUsername(String token) {

		return extractAllClaims(token).getSubject();
	}

	public boolean isTokenValid(String token) {

		try {

			extractAllClaims(token);

			return true;

		} catch (Exception e) {

			return false;
		}
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}