package com.inventor.app.service.impl;

import java.util.List;
import java.util.Optional;

import com.inventor.app.model.Usuario;
import com.inventor.app.repository.CredencialesRepo;
import com.inventor.app.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventor.app.model.Doctor;
import com.inventor.app.repository.DoctorRepo;
import com.inventor.app.service.DoctorService;


@Service
public class DoctorServiceImpl implements DoctorService {



    @Autowired
    private DoctorRepo doctorrepo;

    @Autowired
    private CredencialesRepo credencialesRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;






    @Override
    public Doctor saveDoctor(Doctor pd) {

       return doctorrepo.save(pd);

    }

    @Override
    public List<Doctor> getAllDoctors() {
        return (List<Doctor>) doctorrepo.findAll();
    }

    @Override
    public List<Doctor> buscarPorEspecialidad(String especialidad) {
        return doctorrepo.findByDocEspecialidad(especialidad) ;
    }

    @Override
    public Optional<Doctor> getDoctorbyId(Long id) {
         return doctorrepo.findById(id);
    }

    @Override
    public Doctor updateDoctor(Doctor pd, Long id) {
        return doctorrepo.save(pd);
    }

    @Override
    public void deleteDetail(Long id) {
         doctorrepo.deleteById(id); 
    }

    @Override
    public Doctor buscarPacienteByUsuario(String name) {


        Usuario usuario = usuarioRepo.findByCredenciales( credencialesRepo.findByCreUsername(name).get()).get();
        return doctorrepo.findByDocUsuario(usuario).get();
    }

}
