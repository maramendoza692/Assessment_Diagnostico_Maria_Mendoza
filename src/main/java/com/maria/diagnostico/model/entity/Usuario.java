package com.maria.diagnostico.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name= "usuarios")
@Getter
@Setter
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true,length = 20, nullable = false)
	private String username;

	@Column(length = 60, nullable = false)
	private String password;
	
	private Boolean enabled;
	
	private String nombre;
	
	private String apellido;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name="usuario_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"),
			uniqueConstraints= {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
	private List<Rol> roles;
	
}

