package com.inventor.app.service.impl;

import java.util.List;
import java.util.Optional;

import com.inventor.app.config.Credenciales;
import com.inventor.app.model.Usuario;
import com.inventor.app.repository.CredencialesRepo;
import com.inventor.app.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventor.app.model.Paciente;
import com.inventor.app.repository.PacienteRepo;
import com.inventor.app.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService  {

    @Autowired
    private PacienteRepo pacienteRepo;

    @Autowired
    private CredencialesRepo credencialesRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Override
    public Paciente savePaciente(Paciente pd) {
        return  pacienteRepo.save(pd);
    }

    @Override
    public List<Paciente> getAllPacientes() {
              return  (List<Paciente>)pacienteRepo.findAll();

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
    public Paciente buscarPacienteByUsuario(String username) {


       Usuario usuario = usuarioRepo.findByCredenciales( credencialesRepo.findByCreUsername(username).get()).get();



        return pacienteRepo.findByPacUsuario(usuario).get();
    }

    @Override
    public void deleteDetail(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDetail'");
    }

}
