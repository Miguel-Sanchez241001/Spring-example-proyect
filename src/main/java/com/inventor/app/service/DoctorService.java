package com.inventor.app.service;

import java.util.List;
import java.util.Optional;

import com.inventor.app.model.Doctor;

public interface DoctorService {
    
    Doctor saveDoctor(Doctor pd);

    List<Doctor> getAllDoctors();

    Optional<Doctor> getDoctorbyId(Long id);


    Doctor updateDoctor(Doctor pd,Long id);
    
    void deleteDetail(Long id);

    Doctor buscarPacienteByUsuario(String name);
}
