package com.mb.jwtUtil;

import java.util.Date;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.mb.entity.CustomUserDetails;
import com.mb.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil
{
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	@Value("${jwtSecret}")
	private String jwtSecret;

	@Value("${jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication)
	{
		CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date((new Date().getTime() + jwtExpirationMs)))
				.claim("roles", authorities)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public Claims getJwtClaims(String jwtToken)
	{
		Claims claims = null;
		try
		{
			claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
		}
		catch (ExpiredJwtException e)
		{
			throw new CustomException("Token is Expired");
		}
		catch (SignatureException | MalformedJwtException e)
		{
			throw new CustomException("Invalid Token");
		}
		catch (Exception e)
		{
			throw new CustomException("Internal Error while parsing token ");
		}
		return claims;
	}

	public String getUserNameFromJwtToken(String token)
	{
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String token)
	{
		try
		{
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}
		catch (SignatureException e)
		{
			logger.error("Invalid JWT signature: {}", e.getMessage());
		}
		catch (MalformedJwtException e)
		{
			logger.error("Invalid JWT token: {}", e.getMessage());
		}
		catch (ExpiredJwtException e)
		{
			logger.error("JWT token is expired: {}", e.getMessage());
		}
		catch (UnsupportedJwtException e)
		{
			logger.error("JWT token is unsupported: {}", e.getMessage());
		}
		catch (IllegalArgumentException e)
		{
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;

	}
}
