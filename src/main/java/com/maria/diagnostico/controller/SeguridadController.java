package com.maria.diagnostico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maria.diagnostico.auth.auth.ConfigSecurity;
import com.maria.diagnostico.auth.jwt.JwtProvider;
import com.maria.diagnostico.model.entity.Usuario;
import com.maria.diagnostico.service.UsuarioServiceImpl;
import com.maria.diagnostico.utils.Mensaje;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class SeguridadController {

	
	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	ConfigSecurity configSecurity;
	
	@PostMapping("/login")
	public ResponseEntity<Mensaje> login(@Validated @RequestBody Usuario loginUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(new Mensaje("El usuario y/o contrseña son requeridos", ""));
		}
		
		Authentication authentication= configSecurity.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt= jwtProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new Mensaje("Éxito", jwt));
		
	}
}
