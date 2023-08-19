package com.maria.diagnostico.service;

import java.util.List;

import com.maria.diagnostico.model.entity.Response;
import com.maria.diagnostico.model.entity.Tarea;

public interface ITareaService {
	
	Response<Tarea> consultarTodos();
	
	Response<Tarea> guardarTarea(Tarea tarea);
	
	Response<Integer> eliminarTarea (Integer idTarea);
	
	Response<Tarea> consultarTareaId(Integer idTarea);

	Response<Tarea> editarTarea(Tarea tareaNueva);
}

