package com.maria.diagnostico.auth.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.maria.diagnostico.service.UsuarioServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	
	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token= getToken(request);
			
			if(token!=null && jwtProvider.validateToken(token)) {
				String nombreUsuario= jwtProvider.getNombreUsuarioFromToken(token);
				UserDetails userDateils= usuarioServiceImpl.loadUserByUsername(nombreUsuario);
				UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(userDateils, null,
						userDateils.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		} catch (Exception e) {
			logger.error("Fallo en el método doFilterInternal: "+e.getMessage());
		}
		filterChain.doFilter(request, response);
		
	}
	
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header!=null && header.startsWith("Bearer")) {
			return header.replace("Bearer", "");
		}
		return null;
	}

}
