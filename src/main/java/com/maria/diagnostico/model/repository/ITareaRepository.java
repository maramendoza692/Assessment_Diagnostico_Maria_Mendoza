package com.maria.diagnostico.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maria.diagnostico.model.entity.Tarea;

public interface ITareaRepository extends JpaRepository<Tarea, Integer> {

}
