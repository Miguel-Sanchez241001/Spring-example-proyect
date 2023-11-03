package com.inventor.app.service;

import java.util.List;
import java.util.Optional;

import com.inventor.app.model.Paciente;
import com.inventor.app.model.Usuario;

public interface PacienteService {


    Paciente savePaciente(Paciente pd);

    List<Paciente> getAllPacientes();

    Optional<Paciente> getPacientebyId(Long id);


    Paciente updatePaciente(Paciente pd,Long id);
    Paciente buscarPacienteByUsuario(String username);
    void deleteDetail(Long id);
}
