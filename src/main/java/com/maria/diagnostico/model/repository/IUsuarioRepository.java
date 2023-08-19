package com.maria.diagnostico.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maria.diagnostico.model.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	public Usuario findByUsername(String username);

}
