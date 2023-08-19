package com.maria.diagnostico.auth.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.maria.diagnostico.utils.LoginUsuario;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	private String secret = "estaclavesirveparafirmarnuestrotokenjwtestaclavesirveparafirmarnuestrotokenjwt";
	private int expiration = 36000;

	public String generateToken(Authentication authentication) {
		LoginUsuario usuarioPrincipal = (LoginUsuario) authentication.getPrincipal();
		List<String> roles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return Jwts.builder().setSubject(usuarioPrincipal.getUsername()).claim("roles", roles)
				.claim("idUsuario", usuarioPrincipal.getId()).claim("nombre", usuarioPrincipal.getNombre())
				.setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + expiration * 180))
				.signWith(getSecret(secret), SignatureAlgorithm.HS256).compact();
	}

	private Key getSecret(String secret) {
		byte[] secretBytes = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(secretBytes);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("token mal formado");
		} catch (UnsupportedJwtException e) {
			logger.error("token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("token expirado");
		} catch (IllegalArgumentException e) {
			logger.error("token vacio");
		} 

		
		return false;
	}

	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody()
				.getSubject();
	}

}