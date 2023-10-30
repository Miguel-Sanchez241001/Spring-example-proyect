package com.inventor.app.service;

import java.util.List;

import com.inventor.app.model.Paciente;

public interface PacienteService {


    Paciente savePaciente(Paciente pd);

    List<Paciente> getAllPacientes();

    Paciente getPacientebyId(Long id);


    Paciente updatePaciente(Paciente pd,Long id);
    
    void deleteDetail(Long id);
}
