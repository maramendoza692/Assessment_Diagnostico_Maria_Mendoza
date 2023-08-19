package com.maria.diagnostico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maria.diagnostico.model.entity.Response;
import com.maria.diagnostico.model.entity.Tarea;
import com.maria.diagnostico.model.repository.ITareaRepository;

@Service
public class TareaServiceImpl implements ITareaService{
	
	@Autowired
	private ITareaRepository tareaRepository;
	
	//Consultar todos
	@Override
	public Response<Tarea> consultarTodos() {

		Response<Tarea> response = new Response<Tarea>();

		List<Tarea> lista = tareaRepository.findAll();
		
		if (lista.isEmpty()) {

			response.setMessage("No hay resultados!!");
			response.setStatus("OK");

		} else {

			response.setMessage("Consulta correcta!!");

		}

		response.setStatus("OK");
		response.setList(lista);

		response.setCount(lista.size());
		response.setList(lista);
		response.setStatus("OK");
		response.setMessage("Consulta correcta!!");

		return response;

	}
	//Guardar tarea
	@Override
	public Response<Tarea> guardarTarea(Tarea tarea) {
	    Response<Tarea> response = new Response<Tarea>();
	    
	    Tarea tareaGuardada = tareaRepository.save(tarea);

	    if (tareaGuardada != null) {
	    	response.setStatus("OK");
	        response.setMessage("Tarea guardada exitosamente");
	        response.setData(tareaGuardada);
	        
	    } else {
	        response.setMessage("No se pudo guardar la tarea");
	    }

	    return response;
	}
	
	//Actualizar tarea
	@Override
	public Response<Tarea> editarTarea(Tarea tareaNueva) {
	    Response<Tarea> response = new Response<Tarea>();
	    
	    Optional<Tarea> tareaExistenteOptional = tareaRepository.findById(tareaNueva.getIdTarea());

	    if (tareaExistenteOptional.isPresent()) {
	        Tarea tareaExistente = tareaExistenteOptional.get();
	        
	        tareaExistente.setNombre(tareaNueva.getNombre());
	        tareaExistente.setDescripcion(tareaNueva.getDescripcion());
	        tareaExistente.setEstado(tareaNueva.getEstado());

	        Tarea tareaActualizada = tareaRepository.save(tareaExistente);

	        response.setStatus("OK");
	        response.setMessage("Tarea actualizada exitosamente");
	        response.setData(tareaActualizada);
	    } else {
	        
	        response.setMessage("No se encontró la tarea con ID: " + tareaId);
	    }

	    return response;
	}


	//Eliminar tarea
	@Override
	public Response<Integer> eliminarTarea(Integer idTarea) {
	    Response<Integer> response = new Response<Integer>();

	    // Verificar si la tarea existe antes de eliminar
	    if (tareaRepository.existsById(idTarea)) {
	        tareaRepository.deleteById(idTarea);

	        response.setStatus("OK");
	        response.setMessage("Tarea eliminada exitosamente");
	    } else {
	        
	        response.setMessage("No se encontró la tarea con ID: " + idTarea);
	    }

	    return response;
	}
	
	//Consultar por id
	@Override
	public Response<Tarea> consultarTareaId(Integer idTarea) {

		Response<Tarea> response = new Response<Tarea>();

		Tarea tarea = null;

		Optional<Tarea> optional = tareaRepository.findById(idTarea); // optional es una capsula para saber si regresó o
																		// no un registro

		if (optional.isPresent()) {

			tarea = optional.get();
			response.setStatus("OK");
			response.setMessage("Busqueda correcta!!");
			response.setData(tarea);

		} else {

			response.setMessage("No se encontró la tarea con ID: " + idTarea);

		}

		return response;

	}



}

