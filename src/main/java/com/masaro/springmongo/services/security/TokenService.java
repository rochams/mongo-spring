package com.masaro.springmongo.services.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.masaro.springmongo.domain.User;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secretKey;
	
	// Token generate
	public String generateToken(User user) {
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			String token = JWT.create()
					.withIssuer("auth-login")
					.withSubject(user.getEmail())
					.withExpiresAt(this.expirationToken())
					.sign(algorithm);
			return token;
			
		} catch (JWTCreationException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}
	
	// set expiration time for token
	public Instant expirationToken() {
		return Instant.now().plus(120, ChronoUnit.MINUTES);
	}
	
	// Token validation
	public String validateToken(String token) {
		
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		
		try {
			return JWT.require(algorithm)
					.withIssuer("auth-login")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception) {
			return null;
		}
		
	}

}
