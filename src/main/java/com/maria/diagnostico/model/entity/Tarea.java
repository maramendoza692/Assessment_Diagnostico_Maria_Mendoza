package com.maria.diagnostico.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "tareas")
@Setter
@Getter
public class Tarea implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7642745571827893702L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTarea;
	
	@Column(length = 60, nullable = false)
	private String nombre;
	
	@Column(length = 100, nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private String estado;
	
	
	
}

