package com.inventor.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventor.app.model.Doctor;
import com.inventor.app.repository.DoctorRepo;
import com.inventor.app.service.DoctorService;



@Service
public class DoctorServiceImpl implements DoctorService {



    @Autowired
    private DoctorRepo doctorrepo;








    @Override
    public Doctor saveDoctor(Doctor pd) {

       return doctorrepo.save(pd);

    }

    @Override
    public List<Doctor> getAllDoctors() {
        return (List) doctorrepo.findAll();
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
    
}
