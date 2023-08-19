package com.maria.diagnostico.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mensaje {

	
	private String token;
	private String mensaje;
	
	public Mensaje(String mensaje, String token) {
		this.mensaje=mensaje;
		this.token=token;
	}
	
}
