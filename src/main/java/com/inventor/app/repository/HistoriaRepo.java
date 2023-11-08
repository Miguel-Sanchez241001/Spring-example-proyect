package com.inventor.app.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventor.app.model.Historia;
import com.inventor.app.model.Paciente;

import java.util.Optional;


@Repository
public interface HistoriaRepo  extends CrudRepository<Historia, Long>{


    Optional<Historia>  findByHistPaciente(Paciente histPaciente);
    
} 
