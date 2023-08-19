package com.maria.diagnostico.service;

import com.maria.diagnostico.model.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
}