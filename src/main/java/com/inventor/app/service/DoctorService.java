package com.inventor.app.service;

import java.util.List;

import com.inventor.app.model.Doctor;

public interface DoctorService {
    
    Doctor saveDoctor(Doctor pd);

    List<Doctor> getAllDoctors();

    Doctor getDoctorbyId(Long id);


    Doctor updateDoctor(Doctor pd,Long id);
    
    void deleteDetail(Long id);
}
