package com.maria.diagnostico.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.maria.diagnostico.model.entity.Usuario;

import java.util.List;
import java.util.stream.Collectors;


public class LoginUsuario implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	

	public LoginUsuario(String nombre, String nombreUsuario, String password,
			Collection<? extends GrantedAuthority> authorities, Long id) {
		this.id = id;
		this.nombre = nombre;
		this.username = nombreUsuario;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static LoginUsuario build(Usuario usuario) {
		List<GrantedAuthority> roles = usuario.getRoles().stream()
				.map(rol-> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
		
		return new LoginUsuario(usuario.getNombre(), usuario.getUsername(), usuario.getPassword(), roles,
				usuario.getId());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Long getId() {
		return id;
	}

}

