package com.maria.diagnostico.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maria.diagnostico.model.entity.Usuario;
import com.maria.diagnostico.model.repository.IUsuarioRepository;
import com.maria.diagnostico.utils.LoginUsuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

	private Logger logger= LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	IUsuarioRepository usuarioDao;
	
	@Override
	@Transactional(readOnly= true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		if(usuario==null) {
			throw new UsernameNotFoundException("Error: no existe el usuario"+ username);
		}
		
		logger.info("Usuario: "+ usuario.getUsername());
		
		return LoginUsuario.build(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}
}