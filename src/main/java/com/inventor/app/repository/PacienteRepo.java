package com.inventor.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventor.app.model.Paciente;

@Repository
public interface PacienteRepo  extends CrudRepository<Paciente, Long>{
    
}
