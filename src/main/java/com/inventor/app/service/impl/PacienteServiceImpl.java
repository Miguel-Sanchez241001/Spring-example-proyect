package com.inventor.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.inventor.app.model.Paciente;
import com.inventor.app.repository.PacienteRepo;
import com.inventor.app.service.PacienteService;

public class PacienteServiceImpl implements PacienteService  {

@Autowired
    private PacienteRepo pacienteRepo;
    

    @Override
    public Paciente savePaciente(Paciente pd) {
        return  pacienteRepo.save(pd);
    }

    @Override
    public List<Paciente> getAllPacientes() {
              return  (List)pacienteRepo.findAll();

    }

    @Override
    public Optional<Paciente> getPacientebyId(Long id) {
              return  pacienteRepo.findById(id);

    }

    @Override
    public Paciente updatePaciente(Paciente pd, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePaciente'");
    }

    @Override
    public void deleteDetail(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDetail'");
    }
    
}
