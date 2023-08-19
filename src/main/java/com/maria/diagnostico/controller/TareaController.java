package com.maria.diagnostico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maria.diagnostico.model.entity.Response;
import com.maria.diagnostico.model.entity.Tarea;
import com.maria.diagnostico.service.ITareaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tarea")
public class TareaController {

	@Autowired
	private ITareaService tareaService;
	

	@GetMapping(path = "/consultarTodos", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Response<Tarea>> consultartodos(){
		
		Response<Tarea> response =tareaService.consultarTodos();
		
	
		
		return new ResponseEntity<Response<Tarea>> (response, HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/guardarTarea",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response <Tarea>> guardarTarea (@RequestBody Tarea tarea){ 
		
		Response<Tarea> response = tareaService.guardarTarea(tarea);
		
		return new ResponseEntity<Response<Tarea>> (response, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/buscarTareaPorID/{idTarea}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<Tarea>> consultarTareaPorID (@PathVariable("idTarea") Integer idTarea){ 
		
		
		Response<Tarea> response = tareaService.consultarTareaId(idTarea);
		
		return new ResponseEntity<Response<Tarea>> (response,HttpStatus.OK);
		
	}
	
	@DeleteMapping(path = "/eliminarTarea/{idTarea}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<Integer>> eliminarTareaPorID (@PathVariable("idTarea") Integer idTarea){ 
		
		
		Response<Integer> response = tareaService.eliminarTarea(idTarea);
		
		return new ResponseEntity<Response<Integer>> (response ,HttpStatus.OK);
		
	}
	
		
	@PutMapping(path = "/actualizarTarea",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<Tarea>> actualizarTarea(@RequestBody Integer tareaId, Tarea tareaNueva){
		
		Response<Tarea> tareaModificada = tareaService.editarTarea(tareaId, tareaNueva);
		return new ResponseEntity<Response<Tarea>> (tareaModificada, HttpStatus.OK);
	}
}
