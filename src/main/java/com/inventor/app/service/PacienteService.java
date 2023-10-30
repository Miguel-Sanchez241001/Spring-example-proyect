package com.inventor.app.service;

import java.util.List;
import java.util.Optional;

import com.inventor.app.model.Paciente;

public interface PacienteService {


    Paciente savePaciente(Paciente pd);

    List<Paciente> getAllPacientes();

    Optional<Paciente> getPacientebyId(Long id);


    Paciente updatePaciente(Paciente pd,Long id);
    
    void deleteDetail(Long id);
}
